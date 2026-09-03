package com.ekatayan.app.feature.wishlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class WishlistViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(mockWishlistUiState())
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    fun createGroup(name: String): Boolean {
        val trimmedName = name.trim()
        if (trimmedName.isEmpty()) return false
        _uiState.update { state ->
            val nextId = (state.groups.maxOfOrNull(WishlistGroup::id) ?: 0) + 1
            state.copy(
                groups = state.groups + WishlistGroup(
                    id = nextId,
                    name = trimmedName,
                ),
            )
        }
        return true
    }

    fun renameGroup(groupId: Int, newName: String): Boolean {
        val trimmedName = newName.trim()
        if (trimmedName.isEmpty()) return false
        _uiState.update { state ->
            state.copy(groups = state.groups.map { group ->
                if (group.id == groupId) group.copy(name = trimmedName) else group
            })
        }
        return true
    }

    fun deleteGroup(groupId: Int) {
        _uiState.update { it.copy(groups = it.groups.filterNot { group -> group.id == groupId }) }
    }

    fun updateGroupCoverFromDevice(groupId: Int, imageUri: String) {
        _uiState.update { state ->
            state.copy(groups = state.groups.map { group ->
                if (group.id == groupId) {
                    group.copy(cover = WishlistCover.FromDevice(imageUri))
                } else group
            })
        }
    }

    fun updateGroupCoverFromPlace(groupId: Int, placeId: Int): Boolean {
        val group = _uiState.value.groups.find { it.id == groupId } ?: return false
        if (group.items.none { it.id == placeId }) return false
        _uiState.update { state ->
            state.copy(groups = state.groups.map { current ->
                if (current.id == groupId) current.copy(cover = WishlistCover.FromPlace(placeId)) else current
            })
        }
        return true
    }

    fun searchAvailableDestinations(groupId: Int, query: String): List<WishlistItem> {
        val savedIds = _uiState.value.groups
            .find { it.id == groupId }
            ?.items
            ?.mapTo(hashSetOf(), WishlistItem::id)
            .orEmpty()
        val normalizedQuery = query.trim()
        return _uiState.value.availableDestinations.filter { destination ->
            destination.id !in savedIds && (
                normalizedQuery.isEmpty() ||
                    destination.name.contains(normalizedQuery, ignoreCase = true) ||
                    destination.location?.contains(normalizedQuery, ignoreCase = true) == true ||
                    destination.description.contains(normalizedQuery, ignoreCase = true)
                )
        }
    }

    fun hasDestinationMatch(query: String): Boolean {
        val normalizedQuery = query.trim()
        return normalizedQuery.isEmpty() || _uiState.value.availableDestinations.any { destination ->
            destination.name.contains(normalizedQuery, ignoreCase = true) ||
                destination.location?.contains(normalizedQuery, ignoreCase = true) == true ||
                destination.description.contains(normalizedQuery, ignoreCase = true)
        }
    }

    fun addPlaceToGroup(groupId: Int, item: WishlistItem): Boolean {
        val group = _uiState.value.groups.find { it.id == groupId } ?: return false
        if (group.items.any { it.id == item.id }) return false
        _uiState.update { state ->
            state.copy(groups = state.groups.map { current ->
                if (current.id == groupId) current.copy(items = current.items + item) else current
            })
        }
        return true
    }

    fun removePlaceFromGroup(groupId: Int, itemId: Int) {
        _uiState.update { state ->
            state.copy(groups = state.groups.map { group ->
                if (group.id == groupId) {
                    group.copy(
                        items = group.items.filterNot { it.id == itemId },
                        cover = if ((group.cover as? WishlistCover.FromPlace)?.placeId == itemId) WishlistCover.None else group.cover,
                    )
                } else group
            })
        }
    }
}

private fun mockWishlistUiState(): WishlistUiState {
    val destinations = WishlistDestinationCatalog.destinations
    return WishlistUiState(
        availableDestinations = destinations,
        groups = listOf(
            WishlistGroup(1, "My Favs", WishlistCover.FromPlace(1), destinations.filter { it.id in 1..3 }),
            WishlistGroup(2, "Beach Vibes", WishlistCover.FromPlace(4), destinations.filter { it.id in 4..7 }),
            WishlistGroup(3, "Hilly Vibes", WishlistCover.FromPlace(9), destinations.filter { it.id in 8..11 }),
        ),
    )
}
