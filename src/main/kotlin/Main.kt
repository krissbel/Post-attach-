import java.lang.RuntimeException
import java.util.*

var UNIQUE_ID: Int = 1
val id = 0
val postId = 0

val comment = Comments(1, text = "Hi")
val copyright = Copyright(1, "www.wiki.ru", "Википедия", "сайт")
val repost = Reposts(2, true)
val view = Views(1)

fun main() {

    val post = Post(
        id,
        ownerId = 2,
        fromId = 3,
        createdBy = 4,
        date = 20220503,
        text = "Всем привет",
        replyOwnerId = 5,
        replyPostId = 6,
        friendsOnly = true,
        copyright = copyright,
        reposts = repost,
        views = view,
        postType = "post",
        signerId = 11,
        likes = 20,
        canPin = true,
        markedAsAds = false,
        isPinned = true,
        canDelete = false,
        canEdit = true,
        donut = 1,
        isFavorite = true,
        postponedId = 55
    )
    val post1 = Post(
        id,
        ownerId = 22,
        fromId = 32,
        createdBy = 43,
        date = 20220504,
        text = "Всем привет",
        replyOwnerId = 51,
        replyPostId = 61,
        friendsOnly = true,
        copyright = copyright,
        reposts = repost,
        views = view,
        postType = "post",
        signerId = 11,
        likes = 20,
        canPin = true,
        markedAsAds = false,
        isPinned = true,
        canDelete = false,
        canEdit = true,
        donut = 1,
        isFavorite = true,
        postponedId = 55
    )
    val post2 = Post(
        id,
        ownerId = 22,
        fromId = 32,
        createdBy = 43,
        date = 20220504,
        text = "Всем привет",
        replyOwnerId = 51,
        replyPostId = 61,
        friendsOnly = true,
        copyright = copyright,
        reposts = repost,
        views = view,
        postType = "post",
        signerId = 11,
        likes = 20,
        canPin = true,
        markedAsAds = false,
        isPinned = true,
        canDelete = false,
        canEdit = true,
        donut = 1,
        isFavorite = true,
        postponedId = 55
    )
    WallService.add(post)
    WallService.add(post1)
    WallService.add(post2)
    WallService.update(post2)
    WallService.update(
        Post(
            id = 0,
            ownerId = 30,
            fromId = 32,
            createdBy = 43,
            date = 20220504,
            text = "Всем привет",
            replyOwnerId = 51,
            replyPostId = 61,
            friendsOnly = true,
            copyright = copyright,
            reposts = repost,
            views = view,
            postType = "post",
            signerId = 11,
            likes = 20,
            canPin = true,
            markedAsAds = false,
            isPinned = true,
            canDelete = false,
            canEdit = true,
            donut = 1,
            isFavorite = true,
            postponedId = 55
        )
    )

    for (post in WallService.posts) {
        println(post)
    }

    println(
        WallService.update(
            Post(
                id = 0,
                ownerId = 40,
                fromId = 32,
                createdBy = 43,
                date = 20220506,
                text = "Всем привет",
                replyOwnerId = 51,
                replyPostId = 61,
                friendsOnly = true,
                copyright = copyright,
                reposts = repost,
                views = view,
                postType = "post",
                signerId = 11,
                likes = 20,
                canPin = true,
                markedAsAds = false,
                isPinned = true,
                canDelete = false,
                canEdit = true,
                donut = 1,
                isFavorite = true,
                postponedId = 55
            )
        )
    )

    for (post in WallService.posts) {
        println(post)
    }
    val comment1 = Comments(2, "Круто")
    WallService.createComment(comment1)
    for (comment in WallService.comments) {
        println(comment)
    }
}

data class Post(
    val id: Int?,
    val ownerId: Int?,
    val fromId: Int?,
    val createdBy: Int,
    val date: Int?,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val comments: Array<Comments> = emptyArray(),
    val copyright: Copyright,
    val likes: Int,
    val reposts: Reposts?,
    val views: Views,
    val postType: String,
    val signerId: Int,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val donut: Any,
    val postponedId: Int,
    val attachments: Array<Attachments> = emptyArray()
)

object WallService {
    var posts = emptyArray<Post>()
    var comments = emptyArray<Comments>()

    fun add(post: Post): Post {
        posts += post.copy(id = UNIQUE_ID++)
        return posts.last()
    }


    fun update(post: Post): Boolean {
        for ((index, item) in posts.withIndex()) {
            if (item.id == post.id) {
                posts[index] = post.copy(ownerId = item.ownerId, date = item.date)
                return true
            }
        }
        return false
    }

    fun createComment(comment: Comments) {
        for ((index, item) in posts.withIndex()) {
            if (comment.postId == item.id) {
                comments += comment.copy()
                return
            }
        }
        throw PostNotFoundException("not found PostId")
    }
}

class PostNotFoundException(message: String) : RuntimeException(message) {

}

data class Comments(
    val postId: Int,
    val text: String
)

data class Copyright(
    val id: Int,
    val link: String,
    val name: String,
    val type: String,
)

data class Reposts(
    val count: Int,
    val userReposted: Boolean,
)

data class Views(
    val count: Int,
)

interface Attachments {
    val type: String
}

data class Audio(
    val id: Int?,
    val ownerId: Int?,
    val artist: String?,
    val title: String?,
    val duration: Int,
    val url: String,
    val albumId: Int?,
    val date: Int?
)

data class Video(
    val id: Int,
    val albumId: Int,
    val ownerId: Int?,
    val description: String,
    val duration: Int,
    val date: Int?,
    val views: Int
)

data class Photo(
    val id: Int,
    val albumId: Int
)

data class Doc(
    val id: Int,
    val ownerId: Int?
)

data class Graffiti(
    val id: Int,
    val ownerId: Int?
)

data class AudioAttachment(
    override val type: String = "audio",
) : Attachments


data class VideoAttachment(
    override val type: String = "video",
    val video: Video
) : Attachments

data class PhotoAttachment(
    override val type: String = "photo",
    val photo: Photo
) : Attachments

data class DocAttachment(
    override val type: String = "doc",
    val doc: Doc
) : Attachments


data class GraffitiAttachment(
    override val type: String = "graffiti",
    val graffiti: Graffiti
) : Attachments

