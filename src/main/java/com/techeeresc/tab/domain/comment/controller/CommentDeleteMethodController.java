package com.techeeresc.tab.domain.comment.controller;

import com.techeeresc.tab.domain.comment.dto.mapper.CommentMapper;
import com.techeeresc.tab.domain.comment.service.CommentService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import com.techeeresc.tab.global.status.StatusMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ApiResponses({
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST by Parameter Missing",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST by Type Mismatch",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "404",
                description = "NOT FOUND",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "500",
                description = "INTERNAL SERVER ERROR",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
})
@Tag(name = "comment", description = "Comment API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comment")
public class CommentDeleteMethodController {
    private final CommentService COMMENT_SERVICE;
    private final CommentMapper COMMENT_MAPPER;

    @Operation(
            summary = "Delete Comment",
            description = "해당 id 댓글 삭제",
            responses = {@ApiResponse(responseCode = "200", description = "Delete Success")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteComment(
            @Parameter(description = "댓글 id", in = ParameterIn.PATH) @PathVariable Long id) {
        COMMENT_SERVICE.deleteComment(id);
        return StatusMessage.OK.getStatusMessage();
    }
}
