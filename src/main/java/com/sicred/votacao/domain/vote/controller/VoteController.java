package com.sicred.votacao.domain.vote.controller;

import com.sicred.votacao.domain.vote.controller.request.VoteRequest;
import com.sicred.votacao.domain.vote.controller.response.VoteResponse;
import com.sicred.votacao.domain.vote.model.Vote;
import com.sicred.votacao.domain.vote.service.VoteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping()
    @ApiOperation(value = "votar em uma sessão aberta")
    public VoteResponse create(@RequestBody @Valid VoteRequest voteRequest){
        Vote vote = voteService.saveVote(voteRequest.toVote());
        return VoteResponse.of(vote);

    }
}
