package OOP2.Tests;

import OOP2.Provided.ConnectionAlreadyExistException;
import OOP2.Provided.Person;
import OOP2.Provided.SamePersonException;
import OOP2.Provided.Status;
import OOP2.Solution.*;

public class MainTest {
    public static void main(String[] args) {
        String p_name = "Yossi";
        Integer p_id = 1;
        PersonImpl p = new PersonImpl(p_id, p_name);
        assert p_name.equals(p.getName());
        assert p_id.equals(p.getId());
        assert p.getFriends().size() == 0;
        PersonImpl p2 = new PersonImpl(p_id, "Yossef");
        PersonImpl p_bigger_id = new PersonImpl(p_id+1, "Yossef");
        assert p.equals(p2);
        assert p.compareTo(p_bigger_id) < 0;
        int check_exception = 0;
        try {
            p.addFriend(p2);
        }
        catch(SamePersonException e) {
            check_exception = 1;
        }
        catch(ConnectionAlreadyExistException e2) {
            check_exception = 2;
        }
        assert check_exception == 1;
        check_exception = 0;
        try {
            p.addFriend(p_bigger_id);
        }
        catch(SamePersonException e) {
            check_exception = 1;
        }
        catch(ConnectionAlreadyExistException e2) {
            check_exception = 2;
        }
        assert check_exception == 0;
        check_exception = 0;
        try {
            p.addFriend(p_bigger_id);
        }
        catch(SamePersonException e) {
            check_exception = 1;
        }
        catch(ConnectionAlreadyExistException e2) {
            check_exception = 2;
        }
        assert check_exception == 2;
        assert p.getFriends().size() == 1;
        for(Person pp: p.getFriends()) {
            assert pp.equals(p_bigger_id);
        }
        p.postStatus("Status1");
        p.postStatus("Status2");
        p.postStatus("Status3");
        p.postStatus("Status4").like(p2);
        p.postStatus("Status5").like(p2);
        p.postStatus("Status6");
        int status_counter = 0;
        for(Status st: p.getStatusesRecent()) {
            System.out.println("Status number " + st.getId() + " with content " + st.getContent() + "and likes " +st.getLikesCount());
            status_counter++;
        }
        assert status_counter == 6;
        for(Status st: p.getStatusesPopular()) {
            System.out.println("Status number " + st.getId() + " with content " + st.getContent() + "and likes " +st.getLikesCount());
        }
    }
}
