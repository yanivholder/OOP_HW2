package OOP2.Tests;

import OOP2.Provided.ConnectionAlreadyExistException;
import OOP2.Provided.*;
import OOP2.Provided.SamePersonException;
import OOP2.Provided.Status;
import OOP2.Solution.*;

import java.util.Iterator;

public class MainTest {
    public static void main(String[] args) {
//        String p_name = "Yossi";
//        Integer p_id = 1;
//        PersonImpl p = new PersonImpl(p_id, p_name);
//        assert p_name.equals(p.getName());
//        assert p_id.equals(p.getId());
//        assert p.getFriends().size() == 0;
//        PersonImpl p2 = new PersonImpl(p_id, "Yossef");
//        PersonImpl p_bigger_id = new PersonImpl(p_id+1, "Yossef");
//        assert p.equals(p2);
//        assert p.compareTo(p_bigger_id) < 0;
//        int check_exception = 0;
//        try {
//            p.addFriend(p2);
//        }
//        catch(SamePersonException e) {
//            check_exception = 1;
//        }
//        catch(ConnectionAlreadyExistException e2) {
//            check_exception = 2;
//        }
//        assert check_exception == 1;
//        check_exception = 0;
//        try {
//            p.addFriend(p_bigger_id);
//        }
//        catch(SamePersonException e) {
//            check_exception = 1;
//        }
//        catch(ConnectionAlreadyExistException e2) {
//            check_exception = 2;
//        }
//        assert check_exception == 0;
//        check_exception = 0;
//        try {
//            p.addFriend(p_bigger_id);
//        }
//        catch(SamePersonException e) {
//            check_exception = 1;
//        }
//        catch(ConnectionAlreadyExistException e2) {
//            check_exception = 2;
//        }
//        assert check_exception == 2;
//        assert p.getFriends().size() == 1;
//        for(Person pp: p.getFriends()) {
//            assert pp.equals(p_bigger_id);
//        }
//        p.postStatus("Status1");
//        p.postStatus("Status2");
//        p.postStatus("Status3");
//        p.postStatus("Status4").like(p2);
//        p.postStatus("Status5").like(p2);
//        p.postStatus("Status6");
//        int status_counter = 0;
//        for(Status st: p.getStatusesRecent()) {
//            System.out.println("Status number " + st.getId() + " with content " + st.getContent() + "and likes " +st.getLikesCount());
//            status_counter++;
//        }
//        assert status_counter == 6;
//        for(Status st: p.getStatusesPopular()) {
//            System.out.println("Status number " + st.getId() + " with content " + st.getContent() + "and likes " +st.getLikesCount());
//        }


        FaceOOP f = new FaceOOPImpl();
        Person pE = null, pY = null, pD = null, pI = null, pO = null, pS = null, pF = null, pA = null;
        try{
            pD = f.joinFaceOOP(3, "Dror");
            pI = f.joinFaceOOP(4, "Idan");
            pY = f.joinFaceOOP(1, "Yaniv");
            pE = f.joinFaceOOP(2, "Eilon");

            f.addFriendship(pY, pE);
            f.addFriendship(pY, pD);
            f.addFriendship(pY, pI);
        } catch (Exception ignored){}

        pY.postStatus("Yaniv");
        pE.postStatus("Eilon1");
        pE.postStatus("Eilon2");
        pD.postStatus("Dror1");
        pD.postStatus("Dror2").like(pY);
        pI.postStatus("Idan1");
        pI.postStatus("Idan3");
        pI.postStatus("Idan2").like(pE);

        try{
            for (StatusIterator iter = f.getFeedByPopular(pY); iter.hasNext(); ) {
                System.out.println(iter.next().getContent());
            }
        } catch (Exception e){
            System.out.println("error:"+e);
        }


// ------------test rank()------------------
//        FaceOOP f = new FaceOOPImpl();
//        Person pE = null, pY = null, pD = null, pI = null, pO = null, pS = null, pF = null, pA = null;
//        try{
//            pY = f.joinFaceOOP(1, "Yaniv");
//            pE = f.joinFaceOOP(2, "Eilon");
//            pD = f.joinFaceOOP(3, "Dror");
//            pI = f.joinFaceOOP(4, "Idan");
//            pO = f.joinFaceOOP(5, "Omer");
//            pS = f.joinFaceOOP(6, "Shachar");
//            pF = f.joinFaceOOP(7, "Florentz");
//            pA = f.joinFaceOOP(8, "Aba");
//
//
//            f.addFriendship(pY, pE);
//            f.addFriendship(pY, pS);
//            f.addFriendship(pE, pD);
//            f.addFriendship(pS, pF);
//            f.addFriendship(pD, pI);
//            f.addFriendship(pI, pO);
//            f.addFriendship(pI, pS);
//            f.addFriendship(pA, pD);
////            f.addFriendship(pO, pS);
//        } catch (Exception ignored){}
//
//        int d1=0, d2=0, d3=0, d4=0, d5=0, d6=0, d7=0;
//        try{
//            d1 = f.rank(pO, pF);
//            d2 = f.rank(pY, pF);
//            d3 = f.rank(pA, pD);
//            d4 = f.rank(pD, pS);
//            d5 = f.rank(pA, pS);
//            d6 = f.rank(pY, pO);
//            d7 = f.rank(pA, pF);
//        }catch (Exception ignored){}
//
//        assert (d1 == 3);
//        assert (d2 == 2);
//        assert (d3 == 1);
//        assert (d4 == 2);
//        assert (d5 == 3);
//        assert (d6 == 3);
//        assert (d7 == 4);
//        System.out.println("SUCCES");



//    Example e = new Example();
//    e.ExampleTest();





    }
}
