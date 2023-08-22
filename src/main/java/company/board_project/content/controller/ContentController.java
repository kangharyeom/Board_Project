package company.board_project.content.controller;

import company.board_project.awsS3.AwsS3Service;
import company.board_project.comment.repository.CommentRepository;
import company.board_project.content.dto.ContentPatchDto;
import company.board_project.content.dto.ContentPostDto;
import company.board_project.content.dto.ContentResponseDto;
import company.board_project.content.entity.Content;
import company.board_project.content.mapper.ContentMapper;
import company.board_project.content.repository.ContentFileRepository;
import company.board_project.content.repository.ContentRepository;
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

    // 게시글 생성 //
    @PostMapping
    public ResponseEntity postContent(@Validated @RequestBody ContentPostDto requestBody) {

        Content content = contentService.createContent(contentMapper.contentPostDtoToContent(requestBody));
        ContentResponseDto contentResponse = contentMapper.contentToContentResponse(content, contentFileRepository);

        return new ResponseEntity<>(
                new SingleResponseDto<>(contentResponse) , HttpStatus.CREATED
        );

    }

    // 게시글 파일 첨부 생성
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

        return new ResponseEntity<>(
                new SingleResponseDto<>(contentResponse) , HttpStatus.CREATED
        );
    }

    // 게시글 단건 조회 //
    @GetMapping("/{contentId}")
    public ResponseEntity getContent(@PathVariable("contentId") Long contentId) {
        Content content = contentService.findContent(contentId);

        return new ResponseEntity<>(contentMapper.contentToContentAllResponse(content,contentFileRepository,commentRepository),
                HttpStatus.OK);
    }

    // 게시글 전체 조회 //
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

    // 게시글 검색 기능 //
    @GetMapping("/search")
    public ResponseEntity getSearch(@RequestParam(value = "keyword",required = false) String keyword) {
        List<Content> contents = contentService.findAllSearch(keyword);
        return new ResponseEntity<>(contentMapper.contentListDtoToContentResponse(contents, contentFileRepository),
                HttpStatus.OK);
    }

    // 회원 이름(닉네임) 단위 검색
    @GetMapping("/search/username")
    public ResponseEntity getSearchByUserName(@RequestParam(value = "name",required = false) String name) {
        List<Content> contents = contentService.findAllSearchByUserName(name);
        return new ResponseEntity<>(contentMapper.contentListDtoToContentResponse(contents, contentFileRepository),
                HttpStatus.OK);
    }

    // 최신 순서 필터
    @GetMapping("/newest")
    public ResponseEntity getContentsNewest() {
        List<Content> contents = contentService.findContentsNewest();

        return new ResponseEntity<>(contentMapper.contentsToContentNewestAndLatestResponseDto(contents, contentFileRepository),
                HttpStatus.OK);
    }

    // 오래된 순서 필터
    @GetMapping("/latest")
    public ResponseEntity getContentsLatest() {
        List<Content> contents = contentService.findContentsLatest();

        return new ResponseEntity<>(contentMapper.contentsToContentNewestAndLatestResponseDto(contents, contentFileRepository),
                HttpStatus.OK);
    }

    // 게시글 수정 //
    @PatchMapping("/{contentId}")
    public ResponseEntity patchContent(@RequestBody ContentPatchDto requestBody,
                                       @PathVariable("contentId") Long contentId) {
        requestBody.updateId(contentId);
        Content content = contentService.updateContent(
                contentMapper.contentPatchDtoToContent(requestBody));

        ContentResponseDto contentResponse = contentMapper.contentToContentResponse(content, contentFileRepository);

        return new ResponseEntity<>(contentResponse, HttpStatus.OK);
    }

    // 게시글 삭제 //
    @DeleteMapping("/{contentId}")
    public ResponseEntity deleteContent(@PathVariable("contentId") Long contentId) {
        contentService.deleteContent(contentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
