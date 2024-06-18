package company.board_project.domain.match.leagueMatch.mapper;

import company.board_project.domain.match.leagueMatch.dto.LeagueMatchPostDto;
import company.board_project.domain.match.leagueMatch.dto.LeagueMatchResponseDto;
import company.board_project.domain.match.leagueMatch.entity.LeagueMatch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeagueMatchMapper {
    LeagueMatch leagueMatchPostDtoToLeagueMatch(LeagueMatchPostDto requestBody);
    LeagueMatchResponseDto leagueMatchToLeagueMatchResponseDto(LeagueMatch requestBody);
}
