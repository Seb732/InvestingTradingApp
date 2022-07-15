package repository;

import com.capgemini.investingtradingappposition.entity.Position;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest

public class PositionRepositoryTest {

    Position position;

//    @Autowired
//    PositionRepository positionRepository;

//    @BeforeEach
//    void init() throws IncorrectTeleNumbException, IncorrectEmailException {
//        position = new Position();
//        PersonalAccount personalAccount = new PersonalAccount();
//        InvestingAccount investingAccount = new InvestingAccount();
//        User user = new User();
//        user.setFirstName("testName");
//        user.setLastName("testLastname");
//        user.setTeleNumb("111-222-333");
//        user.setEmail("test@gmail.com");
//        user.setInvestingAccount(investingAccount);
//        user.setPersonalAccount(personalAccount);
//        user.getPersonalAccount().setUser(user);
//        user.getInvestingAccount().setUser(user);
//        PositionStatus positionStatus = PositionStatus.OPEN;
//        position.setPositionStatus(positionStatus);
//        position.setCompanyID(1);
//        position.setSize(1);
//        position.setTicker(1);
//        position.setOpenDate(LocalDateTime.of(2020,1,1,15,37));
//        position.setInvestingAccount(investingAccount);
//        positionRepository.save(position);
//    }

//    @Test
//    void findPositionByCompanyIdSuccess() {
//        List<Position> positions = positionRepository.findPositionByCompanyID(1);
//        assertEquals(1, positions.size());
//        Assertions.assertEquals(position.getPositionID(), positions.get(0).getPositionID());
//    }
//
//    @Test
//    void findPositionByOpenDateAfterSuccess() {
//        List<Position> positions = positionRepository.findPositionByOpenDateAfter(LocalDateTime.of(2000, 1, 1, 1, 1));
//        assertEquals(1, positions.size());
//        Assertions.assertEquals(position.getPositionID(), positions.get(0).getPositionID());
//    }
//
//    @Test
//    void findPositionByTickerGreaterThan() {
//        List<Position> positions = positionRepository.findPositionByTickerGreaterThan(0);
//        assertEquals(1, positions.size());
//        Assertions.assertEquals(position.getPositionID(), positions.get(0).getPositionID());
//    }

}
