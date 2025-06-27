package com.skypro.StarBank.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String user_id;
    private List<RecommendationDTO> recommendations;
    
}
