public class delete_exam extends Exam {
    public delete_exam(examoperation operate)

    {
        super(operate);
    }
    public void exam_func()
    {
        operate.deleteexam();
    }
}
