package com.ekatayan.app.feature.grouphub

import androidx.lifecycle.ViewModel
import com.ekatayan.app.R
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class GroupHubViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(mockState())
    val uiState: StateFlow<GroupHubUiState> = _uiState.asStateFlow()

    fun markRead(groupId: String) = updateGroup(groupId) { it.copy(unreadCount = 0) }
    fun toggleFavourite(groupId: String) = updateGroup(groupId) { it.copy(isFavourite = !it.isFavourite) }
    fun rename(groupId: String, value: String) = value.trim().takeIf(String::isNotEmpty)?.let { name -> updateGroup(groupId) { it.copy(name = name) } }
    fun updateDescription(groupId: String, value: String) = updateGroup(groupId) { it.copy(description = value.trim()) }
    fun updatePhoto(groupId: String, uri: String) = updateGroup(groupId) { it.copy(imageUri = uri) }
    fun updateTheme(groupId: String, theme: ChatTheme) = updateGroup(groupId) { it.copy(theme = theme) }
    fun updateBackground(groupId: String, uri: String?) = updateGroup(groupId) { it.copy(backgroundUri = uri) }
    fun addMembers(groupId: String, ids: Set<String>) = updateGroup(groupId) { it.copy(memberIds = (it.memberIds + ids).distinct()) }
    fun removeMember(groupId: String, userId: String) { if (userId != CURRENT_USER_ID) updateGroup(groupId) { it.copy(memberIds = it.memberIds - userId) } }

    fun createGroup(name: String, description: String, memberIds: Set<String>, imageUri: String?): Boolean {
        val cleanName = name.trim()
        if (cleanName.isEmpty()) return false
        val id = UUID.randomUUID().toString()
        val group = ChatGroup(id, cleanName, description.trim(), imageUri = imageUri, memberIds = (listOf(CURRENT_USER_ID) + memberIds).distinct())
        _uiState.update { it.copy(groups = listOf(group) + it.groups, messagesByGroup = it.messagesByGroup + (id to emptyList())) }
        return true
    }

    fun sendText(groupId: String, text: String, replyTo: String? = null) {
        val clean = text.trim()
        if (clean.isNotEmpty()) addMessage(ChatMessage(UUID.randomUUID().toString(), groupId, CURRENT_USER_ID, MessageType.Text, clean, LocalDateTime.now(), replyToMessageId = replyTo))
    }

    fun sendAttachment(groupId: String, type: MessageType, uri: String? = null, name: String? = null, placeId: Int? = null) =
        addMessage(ChatMessage(UUID.randomUUID().toString(), groupId, CURRENT_USER_ID, type, timestamp = LocalDateTime.now(), attachmentUri = uri, attachmentName = name, placeId = placeId, voiceDurationSeconds = if (type == MessageType.Voice) 8 else null))

    fun react(groupId: String, messageId: String) {
        _uiState.update { state -> state.copy(messagesByGroup = state.messagesByGroup + (groupId to state.messagesByGroup[groupId].orEmpty().map { if (it.id == messageId) it.copy(reactions = if ("❤️" in it.reactions) it.reactions - "❤️" else it.reactions + "❤️") else it })) }
    }

    fun deleteMessage(groupId: String, messageId: String) {
        _uiState.update { it.copy(messagesByGroup = it.messagesByGroup + (groupId to it.messagesByGroup[groupId].orEmpty().filterNot { message -> message.id == messageId })) }
    }

    fun leaveOrDelete(groupId: String) { _uiState.update { it.copy(groups = it.groups.filterNot { group -> group.id == groupId }, messagesByGroup = it.messagesByGroup - groupId) } }

    private fun addMessage(message: ChatMessage) {
        _uiState.update { state ->
            val group = state.groups.firstOrNull { it.id == message.groupId }
            state.copy(
                groups = if (group == null) state.groups else listOf(group.copy(unreadCount = 0)) + state.groups.filterNot { it.id == message.groupId },
                messagesByGroup = state.messagesByGroup + (message.groupId to (state.messagesByGroup[message.groupId].orEmpty() + message)),
            )
        }
    }

    private fun updateGroup(id: String, block: (ChatGroup) -> ChatGroup) { _uiState.update { it.copy(groups = it.groups.map { group -> if (group.id == id) block(group) else group }) } }
}

private fun mockState(): GroupHubUiState {
    val users = listOf(
        ChatUser(CURRENT_USER_ID, "Current User", GroupRole.Owner), ChatUser("ashley", "Ashley"), ChatUser("dan", "Dan"),
        ChatUser("juniper", "Juniper"), ChatUser("peter", "Peter"), ChatUser("sarah", "Sarah"), ChatUser("tim", "Tim"),
        ChatUser("yamal", "Yamal"), ChatUser("jennie", "Jennie"), ChatUser("kasun", "Kasun"),
    )
    val groups = listOf(
        ChatGroup("fam-outings", "Fam Outings", "Weekend adventures with the family", R.drawable.hiking, memberIds = users.map(ChatUser::id), unreadCount = 3, isFavourite = true),
        ChatGroup("work-trip", "Work Trip", "Colombo client visit", R.drawable.colombo, memberIds = listOf(CURRENT_USER_ID, "ashley", "dan", "kasun"), unreadCount = 1),
        ChatGroup("baddies", "Baddies", "Sunsets and spontaneous trips", R.drawable.mirissa, memberIds = listOf(CURRENT_USER_ID, "sarah", "jennie"), isFavourite = true),
        ChatGroup("kawadahari", "Kawadahari", memberIds = listOf(CURRENT_USER_ID, "peter", "kasun")),
        ChatGroup("yanawa-yanawa", "Yanawa Yanawa", memberIds = listOf(CURRENT_USER_ID, "tim", "yamal"), unreadCount = 2),
    )
    fun msg(id: String, group: String, sender: String, text: String, minutesAgo: Long) = ChatMessage(id, group, sender, MessageType.Text, text, LocalDateTime.now().minusMinutes(minutesAgo))
    val messages = mapOf(
        "fam-outings" to listOf(msg("f1", "fam-outings", CURRENT_USER_ID, "I wanna go hiking this time for sure", 25), msg("f2", "fam-outings", "ashley", "OMG!! Really", 18), msg("f3", "fam-outings", "tim", "Well, I wanna go to the beach", 8)),
        "work-trip" to listOf(msg("w1", "work-trip", "dan", "The client meeting is at 10 AM", 55), msg("w2", "work-trip", CURRENT_USER_ID, "I’ll share the itinerary tonight", 41)),
        "baddies" to listOf(msg("b1", "baddies", "jennie", "Mirissa this weekend? 🌊", 80), msg("b2", "baddies", "sarah", "I’m in!", 70)),
        "kawadahari" to listOf(msg("k1", "kawadahari", "peter", "Kawadahari yamu!", 140)),
        "yanawa-yanawa" to listOf(msg("y1", "yanawa-yanawa", "yamal", "Train tickets are booked", 200)),
    )
    return GroupHubUiState(groups, users, messages, mapOf("fam-outings" to setOf("ashley")))
}
