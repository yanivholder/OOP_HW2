package OOP2.Tests;

import OOP2.Solution.*;

public class MainTest {
    public static void main(String[] args) {
        String p_name = "Yossi";
        Integer p_id = 1;
        PersonImpl p = new PersonImpl(p_id, p_name);
        assert p_name.equals(p.getName());
        assert p_id.equals(p.getId());
        assert p.getFriends().size() == 0;
    }
}
