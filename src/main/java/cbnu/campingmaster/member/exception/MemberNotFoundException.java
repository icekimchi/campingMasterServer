package cbnu.campingmaster.member.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(){
        super("존재하지 않는 아이디입니다.");
    }
}
