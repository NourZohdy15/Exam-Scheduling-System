public class assign_factory {

    public assign_assignment assign_func(String type) {
        if (type == null)
        {
            return null;
        }
        if (type == "grade one")
        {
            return new assign_grade1();
        }
        else if (type == "grade two")
        {
            return new assign_grade2();
        }
        else if (type == "grade three")
        {
            return new assign_grade3();
        }
        return null;
    }
}