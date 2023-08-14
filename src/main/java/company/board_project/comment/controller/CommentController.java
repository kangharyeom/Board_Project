package company.board_project.comment.controller;

import company.board_project.comment.dto.CommentPatchDto;
import company.board_project.comment.dto.CommentPostDto;
import company.board_project.comment.mapper.CommentMapper;
import company.board_project.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
//    private final CommentService commentService;
//    private final CommentMapper commentMapper;

    // 댓글 생성 //
    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentPostDto requestBody ){

        return null;
//        return new ResponseEntity(, HttpStatus.CREATED);
    }

    // 댓글 수정 //
    @PatchMapping("/{commentId}")
    public ResponseEntity patchComment(@Valid @RequestBody CommentPatchDto requestBody,
                                       @PathVariable("commentId") @Positive Long commentId){
        return null;
//        return new ResponseEntity<>(, HttpStatus.OK);
    }

    // 댓글 조회 //
    @GetMapping("/{commentId}")
    public ResponseEntity getComment(@PathVariable("commentId") @Positive Long commentId){
        return null;
//        return new ResponseEntity<>(, HttpStatus.OK);
    }

    // 특정 게시글 ID에 있는 댓글 전체 조회 //
    @GetMapping("/contents/{contentId}")
    public ResponseEntity getContentComments(@PathVariable("contentId") @Positive int contentId) {
        return null;
//        return new ResponseEntity<>(, HttpStatus.OK);
    }

    // 댓글 전체 조회 //
    @GetMapping
    public ResponseEntity getComments() {
        return null;
//        return new ResponseEntity<>(, HttpStatus.OK);
    }

    // 댓글 삭제 //
    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") @Positive Long commentId) {
        return null;
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
