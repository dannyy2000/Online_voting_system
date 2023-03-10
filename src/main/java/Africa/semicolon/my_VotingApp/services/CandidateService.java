package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.CandidateRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.CandidateResponseDto;
import Africa.semicolon.my_VotingApp.data.models.Candidate;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;

public interface CandidateService {

    CandidateResponseDto createCandidate(CandidateRequestDto candidateRequestDto);

    CandidateResponseDto getCandidateById(Long candidateId);

    Page<Candidate>getAllCandidates(int pageNumber);

    CandidateResponseDto updateCandidate(Long candidateId,CandidateRequestDto candidateRequestDto);

//    Candidate updateCandidate(Long CandidateId, JsonPatch uploadPayload);

    void deleteCandidate(Long candidateId);
}
