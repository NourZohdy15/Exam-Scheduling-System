public class WriteNoteCommand implements Command{



    @Override
    public void execute(String note_content)
    {
        System.out.println("Write a Note to the Principal: "+ note_content);
    }
}
