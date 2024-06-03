public abstract class student_grade {
    exam_type ex;

    public student_grade(exam_type ex)
    {
        this.ex = ex;
    }

    public abstract void grade_num(int current_id);


}
