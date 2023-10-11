package company.board_project.domain.content.controller;

import company.board_project.domain.content.dto.*;
import company.board_project.domain.content.entity.Content;
import company.board_project.domain.content.mapper.ContentMapper;
import company.board_project.domain.content.repository.ContentFileRepository;
import company.board_project.domain.content.repository.ContentRepository;
import company.board_project.global.aws_s3.AwsS3Service;
import company.board_project.domain.comment.repository.CommentRepository;
import company.board_project.domain.content.service.ContentService;
import company.board_project.global.exception.BusinessLogicException;
import company.board_project.global.exception.Exceptions;
import company.board_project.global.response.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private final ContentMapper contentMapper;
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;
    private final ContentFileRepository contentFileRepository;
    private final AwsS3Service awsS3Service;

    @PostMapping
    public ResponseEntity postContent(@Validated @RequestBody ContentPostDto requestBody) {

        Content content = contentService.createContent(contentMapper.contentPostDtoToContent(requestBody));
        ContentResponseDto contentResponse = contentMapper.contentToContentResponse(content, contentFileRepository);

        return ResponseEntity.ok(contentResponse);
    }

    @PostMapping("/file")
    public ResponseEntity postContentFile(@RequestPart("data") ContentPostDto requestBody,
                                      @RequestPart(required=false, value="ContentFileUrl") List<MultipartFile> multipartFiles ) {

        if (multipartFiles == null) {
            throw new BusinessLogicException(Exceptions.CONTENT_FILE_NOT_FOUND);
        }

        List<String> filePaths = awsS3Service.uploadFile(multipartFiles);
        log.info("IMG 경로들 : "+ filePaths);

        Content content = contentService.createContentFile(contentMapper.contentPostDtoToContent(requestBody),filePaths);
        ContentResponseDto contentResponse = contentMapper.contentToContentResponse(content, contentFileRepository);

        return ResponseEntity.ok(contentResponse);
    }

    @GetMapping("/{contentId}")
    public ResponseEntity getContent(@PathVariable("contentId") Long contentId) {
        Content content = contentService.findContent(contentId);
        ContentAllResponseDto contentAllResponseDto = contentMapper.contentToContentAllResponse(content, contentFileRepository, commentRepository);

        return ResponseEntity.ok(contentAllResponseDto);
    }

    @GetMapping
    public ResponseEntity getContents(@Positive @RequestParam(value = "page", defaultValue = "1") int page,
                                      @Positive @RequestParam(value = "size", defaultValue = "40") int size){

        Page<Content> pageContents = contentService.findContents(page - 1, size);
        List<Content> contents = pageContents.getContent();
        log.info("전체 요청 :" + contents);
        return new ResponseEntity<>(
                new MultiResponseDto<>(contentMapper.contentsToContentsResponse(contents, contentFileRepository),
                        pageContents),
                HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity getSearch(@RequestParam(value = "keyword",required = false) String keyword) {
        List<Content> contents = contentService.findAllSearch(keyword);
        ContentListDto contentListDto = contentMapper.contentListDtoToContentResponse(contents, contentFileRepository);

        return ResponseEntity.ok(contentListDto);
    }

    @GetMapping("/search/username")
    public ResponseEntity getSearchByUserName(@RequestParam(value = "name",required = false) String name) {
        List<Content> contents = contentService.findAllSearchByUserName(name);
        ContentListDto contentListDto = contentMapper.contentListDtoToContentResponse(contents, contentFileRepository);

        return ResponseEntity.ok(contentListDto);
    }

    @GetMapping("/newest")
    public ResponseEntity getContentsNewest() {
        List<Content> contents = contentService.findContentsNewest();
        List<ContentResponseDto> contentResponseDtos = contentMapper.contentsToContentsResponse(contents, contentFileRepository);

        return ResponseEntity.ok(contentResponseDtos);
    }

    @GetMapping("/latest")
    public ResponseEntity getContentsLatest() {
        List<Content> contents = contentService.findContentsLatest();
        List<ContentResponseDto> contentResponseDtos = contentMapper.contentsToContentsResponse(contents, contentFileRepository);

        return ResponseEntity.ok(contentResponseDtos);
    }

    @GetMapping("/category")
    public ResponseEntity getContentsByCategory(@RequestParam(value = "category",required = false) String category) {
        List<Content> contents = contentService.findContentsByCategory(category);
        List<ContentResponseDto> contentResponseDtos = contentMapper.contentsToContentsResponse(contents, contentFileRepository);

        return ResponseEntity.ok(contentResponseDtos);
    }

    @PatchMapping("/{contentId}")
    public ResponseEntity patchContent(@RequestBody ContentPatchDto requestBody,
                                       @PathVariable("contentId") Long contentId) {
        requestBody.updateId(contentId);
        Content content = contentService.updateContent(
                contentMapper.contentPatchDtoToContent(requestBody));

        ContentResponseDto contentResponse = contentMapper.contentToContentResponse(content, contentFileRepository);

        return ResponseEntity.ok(contentResponse);
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity deleteContent(@PathVariable("contentId") Long contentId) {
        contentService.deleteContent(contentId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
