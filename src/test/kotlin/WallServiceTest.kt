import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add() {
        WallService.posts = emptyArray()
        WallService.add(
            Post(
                id,
                ownerId = 2,
                fromId = 3,
                createdBy = 4,
                date = 20220503,
                text = "Всем привет",
                replyOwnerId = 5,
                replyPostId = 6,
                friendsOnly = true,
                comments = emptyArray(),
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

        val result = WallService.posts.size
        assertEquals(1, result)

    }

    @Test
    fun update() {
        WallService.posts = emptyArray()
        WallService.add(
            Post(
                id,
                ownerId = 2,
                fromId = 3,
                createdBy = 4,
                date = 20220503,
                text = "Всем привет",
                replyOwnerId = 5,
                replyPostId = 6,
                friendsOnly = true,
                comments = emptyArray(),
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

        val result = WallService.update(
            Post(
                id = 1,
                ownerId = 30,
                fromId = 32,
                createdBy = 43,
                date = 20220504,
                text = "Всем привет",
                replyOwnerId = 51,
                replyPostId = 61,
                friendsOnly = true,
                comments = emptyArray(),
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


        assertTrue(result)
    }

    @Test
    fun createCommentPostIdFound() {
        WallService.posts = emptyArray()
        WallService.add(
            Post(
                id,
                ownerId = 2,
                fromId = 3,
                createdBy = 4,
                date = 20220503,
                text = "Всем привет",
                replyOwnerId = 5,
                replyPostId = 6,
                friendsOnly = true,
                comments = emptyArray(),
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

        WallService.createComment(Comments(1, "Hi"))
        assertEquals(postId, id)
    }
}