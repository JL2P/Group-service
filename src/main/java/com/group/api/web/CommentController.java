package com.group.api.web;


import com.group.api.domain.Comment;
import com.group.api.domain.GroupTodo;
import com.group.api.domain.SubComment;
import com.group.api.domain.service.CommentService;
import com.group.api.domain.service.GroupTodoService;
import com.group.api.web.dto.groupTodo.CommentAddDto;
import com.group.api.web.dto.groupTodo.CommentDto;
import com.group.api.web.dto.groupTodo.SubCommentAddDto;
import com.group.api.web.dto.groupTodo.SubCommentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"2. Comment"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups/{groupId}/todos") //컨트롤러 기본 URL
public class CommentController {

    private final GroupTodoService grouoTodoService;
    private final CommentService commentService;


    @ApiOperation(value = "댓글 전체 리스트 조회", notes = "해당 Todo의 전체 댓글,대댓글을 불러온다.")
    @GetMapping("/{todoId}/comments")
    public List<CommentDto> getComments(@PathVariable Long todoId) {

        List<Comment> comments = commentService.getComments(grouoTodoService.getTodo(todoId));
        return comments.stream().map(comment -> new CommentDto(comment)).collect(Collectors.toList());
    }

    @ApiOperation(value = "댓글 작성", notes = "해당 Todo에 댓글을 작성한다.")
    @PostMapping("/{todoId}/comments")
    public CommentDto addComment(@PathVariable Long todoId, @RequestBody CommentAddDto commentAddDto) {
        GroupTodo todo = grouoTodoService.getTodo(todoId);
        Comment comment = commentAddDto.toEntity(todo);
        Comment newComment = commentService.addComment(comment);
        return new CommentDto(newComment);
    }


    @ApiOperation(value = "댓글 삭제", notes = "해당 Todo에 댓글을 삭제한다.")
    @DeleteMapping("/{todoId}/comments/{commentId}")
    public String CommentDeleteDto(@PathVariable Long todoId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "Success";
    }


    @ApiOperation(value = "대댓글 작성", notes = "Todo의 댓글에 대댓글을 작성한다. ")
    @PostMapping("/{todoId}/comments/{commentId}/subComments")
    public SubCommentDto addCommentToSubComment(@PathVariable Long todoId, @PathVariable Long commentId, @RequestBody SubCommentAddDto subCommentAddDto) {
        Comment comment = commentService.getComment(commentId);
        SubComment newSubComment = commentService.addSubComment(subCommentAddDto.toDomain(comment));
        return new SubCommentDto(newSubComment);
    }


    @ApiOperation(value = "대댓글 삭제", notes = "해당 Todo에 대댓글을 삭제한다.")
    @DeleteMapping("/{todoId}/comments/{commentId}/subComments/{subCommentId}")
    public String CommentDeleteDto(@PathVariable Long todoId, @PathVariable Long commentId, @PathVariable Long subCommentId) {
        commentService.deleteSubComment(subCommentId);
        return "Success";
    }
}