package company.board_project.content.dto;

import company.board_project.content.entity.ContentFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ContentResponseDto {
    private Long contentId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    public List<ContentFile> contentFileList;
}
