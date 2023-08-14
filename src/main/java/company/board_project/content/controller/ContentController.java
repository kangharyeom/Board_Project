package company.board_project.content.controller;

import company.board_project.content.dto.ContentPatchDto;
import company.board_project.content.dto.ContentPostDto;
import company.board_project.content.mapper.ContentMapper;
import company.board_project.content.service.ContentService;
import company.board_project.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@Validated
@Slf4j
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private final ContentMapper contentMapper;

    // 게시글 생성 //
    @PostMapping
    public ResponseEntity postContent(@RequestBody ContentPostDto requestBody) {

        return new ResponseEntity<>(
                new SingleResponseDto<>(, HttpStatus.CREATED
            )
        );
    }

    // 게시글 단건 조회 //
    @GetMapping("/{contentId}")
    public ResponseEntity getContent(@PathVariable("contentId") Long contentId) {
        return new ResponseEntity<>(, HttpStatus.OK);
    }

    // 게시글 전체 조회 //
    @GetMapping
    public ResponseEntity getContents(@Positive @RequestParam("page") int page,
                                      @Positive @RequestParam("size") int size) {

        return new ResponseEntity<>(
                HttpStatus.OK);
    }

    // 게시글 수정 //
    @PatchMapping("/{contentId}")
    public ResponseEntity patchContent(@RequestBody ContentPatchDto requestBody,
                                       @PathVariable("contentId") Long contentId) {

        return new ResponseEntity<>(, HttpStatus.OK);
    }

    // 게시글 삭제 //
    @DeleteMapping("/{contentId}")
    public ResponseEntity deleteContent(@PathVariable("contentId") Long contentId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
