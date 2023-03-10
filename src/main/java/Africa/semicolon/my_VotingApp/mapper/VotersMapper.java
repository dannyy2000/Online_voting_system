package Africa.semicolon.my_VotingApp.mapper;

import Africa.semicolon.my_VotingApp.data.dto.request.VoterRequestDto;
import Africa.semicolon.my_VotingApp.data.models.Voter;
import Africa.semicolon.my_VotingApp.exception.GeneralServiceException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VotersMapper {

   public static Voter map(VoterRequestDto voterRegisterRequest){
      Voter voter = new Voter();
      voter.setFirstName(voterRegisterRequest.getFirstName());
      voter.setLastName(voterRegisterRequest.getLastName());
      voter.setPhoneNumber(voterRegisterRequest.getPhoneNumber());
      voter.setEmail(voterRegisterRequest.getEmail());
      voter.setGender(voterRegisterRequest.getGender());
      voter.setAddress(voterRegisterRequest.getAddress());

      return voter;

   }


}
