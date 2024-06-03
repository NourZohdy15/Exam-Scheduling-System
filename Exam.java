public abstract class Exam{
    protected examoperation operate;
    int x;

    public Exam(examoperation operate) {
        this.operate = operate;
    }

    public abstract void exam_func();
}
