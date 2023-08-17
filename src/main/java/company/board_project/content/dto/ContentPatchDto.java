package company.board_project.content.dto;

import company.board_project.content.entity.ContentFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class ContentPatchDto {
    private Long contentId;
    private Long userId;
    @NotBlank(message = "게시글 제목을 입력해야 합니다.")
    private String title;
    @NotBlank(message = "게시글의 내용을 입력해야 합니다.")
    private String content;
    public List<ContentFile> contentFileList;

    // 유저 정보
    private String name;

    // 생성자 //
    public void updateId(Long id){
        this.contentId = id;
    }
}
