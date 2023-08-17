package company.board_project.content.controller;

import company.board_project.awsS3.AwsS3Service;
import company.board_project.comment.repository.CommentRepository;
import company.board_project.content.dto.ContentPatchDto;
import company.board_project.content.dto.ContentPostDto;
import company.board_project.content.dto.ContentResponseDto;
import company.board_project.content.entity.Content;
import company.board_project.content.mapper.ContentMapper;
import company.board_project.content.repository.ContentFileRepository;
import company.board_project.content.service.ContentService;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.response.MultiResponseDto;
import company.board_project.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private final ContentMapper contentMapper;
    private final CommentRepository commentRepository;
    private final ContentFileRepository contentFrileRepository;
    private final AwsS3Service awsS3Service;

    // 게시글 생성 //
    @PostMapping
    public ResponseEntity postContent(@RequestPart("data") ContentPostDto requestBody,
                                      @RequestPart(required=false, value="ContentFileUrl") List<MultipartFile> multipartFiles ) {

        if (multipartFiles == null) {
            throw new BusinessLogicException(Exceptions.CONTENT_FILE_NOT_FOUND);
        }

        List<String> filePaths = awsS3Service.uploadFile(multipartFiles);
        log.info("IMG 경로들 : "+ filePaths);

        Content content = contentService.createContent(contentMapper.contentPostDtoToContent(requestBody),filePaths);
        ContentResponseDto contentResponse = contentMapper.contentToContentResponse(content, contentFrileRepository);

        return new ResponseEntity<>(
                new SingleResponseDto<>(contentResponse) , HttpStatus.CREATED
        );
    }

    // 게시글 단건 조회 //
    @GetMapping("/{contentId}")
    public ResponseEntity getContent(@PathVariable("contentId") Long contentId) {
        Content content = contentService.findContent(contentId);

        return new ResponseEntity<>(contentMapper.contentToContentAllResponse(content,contentFrileRepository,commentRepository),
                HttpStatus.OK);
    }

    // 게시글 전체 조회 //
    @GetMapping
    public ResponseEntity getContents(@Positive @RequestParam("page") int page,
                                      @Positive @RequestParam("page") int size){

        Page<Content> pageContents = contentService.findContents(page - 1, size);
        List<Content> contents = pageContents.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(contentMapper.contentsToContentsResponse(contents, contentFrileRepository),
                        pageContents),
                HttpStatus.OK);
    }

    // 게시글 수정 //
    @PatchMapping("/{contentId}")
    public ResponseEntity patchContent(@RequestBody ContentPatchDto requestBody,
                                       @PathVariable("contentId") Long contentId) {
        requestBody.updateId(contentId);
        Content content = contentService.updateContent(
                contentMapper.contentPatchDtoToContent(requestBody));

        ContentResponseDto contentResponse = contentMapper.contentToContentResponse(content, contentFrileRepository);

        return new ResponseEntity<>(contentResponse, HttpStatus.OK);
    }

    // 게시글 삭제 //
    @DeleteMapping("/{contentId}")
    public ResponseEntity deleteContent(@PathVariable("contentId") Long contentId) {
        contentService.deleteContent(contentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
