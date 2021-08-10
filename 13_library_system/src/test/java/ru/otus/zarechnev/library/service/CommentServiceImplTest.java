package ru.otus.zarechnev.library.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private UserCommunicator userCommunicator;
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private CommentServiceImpl commentService;

//    @Test
//    void editById() {
//        // given
//        when(commentRepository.findById(14L))
//                .thenReturn(Optional.of(new Comment().setId(14L).setCommentText("oldText")));
//        when(userCommunicator.getAnswer("Write comment please:"))
//                .thenReturn("newText");
//
//        // when
//        commentService.editById(14L);
//
//        // then
//        verify(commentRepository, times(1)).save(new Comment()
//                .setId(14L)
//                .setCommentText("newText"));
//    }
//
//    @Test
//    void addByBookId() {
//        // given
//        when(bookRepository.findById(13L))
//                .thenReturn(Optional.of(new Book().setId(13L)));
//        when(userCommunicator.getAnswer("Write comment please:"))
//                .thenReturn("newText");
//
//        // when
//        commentService.addByBookId(13L);
//
//        // then
//        verify(commentRepository, times(1)).save(new Comment()
//                .setCommentText("newText")
//                .setBook(new Book().setId(13L)));
//    }
}