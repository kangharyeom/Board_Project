package company.board_project.util;

import company.board_project.constant.AgeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommonUtils {
    public AgeType ageChecker(int age){
        AgeType ageType = null;
        if (age >= 0 && age < 10) {
            ageType = AgeType.YOUTH;
        } else if (age >= 10 && age < 20) {
            ageType = AgeType.TEENAGER;
        } else if (age >= 20 && age < 30) {
            ageType = AgeType.TWENTIES;
        } else if (age >= 30 && age < 40) {
            ageType = AgeType.THIRTIETH;
        } else if (age >= 40 && age < 50) {
            ageType = AgeType.FORTIES;
        } else if (age >= 50 && age < 60) {
            ageType = AgeType.FIFTIES;
        } else {
            ageType = AgeType.SILVER;
        }
        return ageType;
    }
}
