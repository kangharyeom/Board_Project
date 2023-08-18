package company.board_project.content.dto;

import company.board_project.content.entity.ContentFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ContentNewestAndLatestResponseDto {
    private Long contentId;
    private String title;
    private String content;
    private List<ContentFile> contentFileList;
}
