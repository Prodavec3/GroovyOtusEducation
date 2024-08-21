import org.example.ATM
import org.junit.Assert

class AtmTest {

    ATM atm = new ATM()

    @org.junit.jupiter.api.Test
     void hasATM(){
        Assert.assertEquals(atm.countATM, 2);
    }

    @org.junit.jupiter.api.Test
    void testAcceptBanknotes() {
        atm.acceptBanknotes("10":5, "5000":10, "50":20)
        Assert.assertEquals(atm.getMoneyInAtm(), 10*5+5000*10+50*20);
    }
}
