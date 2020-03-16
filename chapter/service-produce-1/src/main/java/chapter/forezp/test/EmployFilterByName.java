package chapter.forezp.test;

/**
 * Created by JF on 2019/11/14.
 */
public class EmployFilterByName implements EmployStrategy{
    @Override
    public boolean filterEmploy(Employ employ) {
        return employ.getName().equals("田七");
    }
}
