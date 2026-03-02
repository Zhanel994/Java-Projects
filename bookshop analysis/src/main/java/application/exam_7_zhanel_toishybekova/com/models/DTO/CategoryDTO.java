package application.exam_7_zhanel_toishybekova.com.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data transfer object for Category entity")
public record CategoryDTO(
        @Schema(description = "Unique ID of the category", example = "e8a5f9e6-4c4e-4c89-97c1-a1e7288d4781")
        String id,

        @Schema(description = "Title of the category", example = "Science-fiction")
        String name
) {
}
