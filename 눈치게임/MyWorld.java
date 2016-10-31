import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    static int mode = 0;
    static boolean g = false;
    static int rN = 0;
    static int rN2 = 0;
    int rt = 0;
    int rt2 = 100;
    boolean rtt = true;
    boolean rtt2 = true;
    static int t = 100;
    
    private SimpleTimer timer = new SimpleTimer();
    
    Counter text = new Counter("오리지널전은 UP키 아이템전은 DOWN키를 눌러주세요.");
    Counter text2= new Counter("랜덤전은 LEFT키 피로 기능 설정 RIGHT");
    Counter cP = new Counter("플레이어 수를 입력해 주세요.(1~8)");
    Counter sM = new Counter("모드를 선택해 주세요.");
    
    Counter p1die = new Counter("p1 die");
    Counter p2die = new Counter("p2 die");
    Counter p3die = new Counter("p3 die");
    Counter p4die = new Counter("p4 die");
    Counter p5die = new Counter("p5 die");
    Counter p6die = new Counter("p6 die");
    Counter p7die = new Counter("p7 die");
    Counter p8die = new Counter("p8 die");
    
    Counter ranDie = new Counter("누군가 죽습니다.");
    
    int cp =0;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 400, 1); 
        
        rule.reset();
        
    }
    
    public void act()
    {
        if(cp==0)cPlayer();
        modeCount();
        win();
        die();
        
        if(mode==3)rt(100);
        
        rePlayer();
        
        debug();
    }
    
    public void cPlayer()
    {
        addObject(cP,880,170);
        String c = Greenfoot.getKey();
        if(c!=null) {
            switch(c) {
                case "1": cp=1; break;
                case "2": cp=2; break;
                case "3": cp=3; break;
                case "4": cp=4; break;
                case "5": cp=5; break;
                case "6": cp=6; break;
                case "7": cp=7; break;
                case "8": cp=8; break;
            }
            removeObject(cP);
        }
    }
    
    public void debug()
    {
        showText(""+g,150,380);
        showText(""+mode,100,380);
        showText(""+rN,750,30);
        showText(""+t,750,50);
        showText(""+rt2,750,70);
        showText(""+rN2,750,90);
        showText(""+rtt,750,110);
        showText(""+rtt2,750,130);
        showText("",750,150);
        showText(""+cp,750,170);
    }
    
    public void rt(int x)
    {
        rN2 = ranN(9);
        if(rt2>0) {
            if(rN==1&&rtt2) {
                addObject(ranDie,650,20);
                rtt=false;
                rtt2=false;
            }
            rt2--;
        }else if(rt2==0) {
            rtt=true;
        }
        if(rtt) {
            if(rt>0) {
                rt--;
            }else if(rt==0) {
                rt=x;
                rt2=100;
                rN = ranN(10);
                rtt2=true;
                removeObject(ranDie);
            }
            t=rt;
        }
    }
    
    public int ranN(int x)
    {
        return Greenfoot.getRandomNumber(x);
    }
    
    public void modeCount()
    {
        String d = Greenfoot.getKey();
        if(cp!=0)addObject(sM,870,170);
        if(mode==0) {
            addObject(text, 300, 20);
            addObject(text2,300, 40);
            if(d=="up")    mode = 1;
            if(d=="down")  mode = 2;
            if(d=="left")  mode = 3;
        }
        if(d=="right")  g = !g;
        if(getObjects(player1.class).isEmpty()&&
        getObjects(player2.class).isEmpty()&&
        getObjects(player3.class).isEmpty()&&  
        getObjects(player4.class).isEmpty()&&
        getObjects(player5.class).isEmpty()&&
        getObjects(player6.class).isEmpty()&&
        getObjects(player7.class).isEmpty()&&
        getObjects(player8.class).isEmpty()) {
            getPlayer(mode);
        }
        if(mode!=0) removeObject(sM);
    }
    
    public void getPlayer(int i)
    {
        switch(i) {
            case 1: setPlayer(); setMusic(); break;
            case 2: setPlayer(); addItem(); setMusic(); break;
            case 3: setPlayer(); setMusic(); break;
        }
    }
    
    public void setMusic()
    {
        GreenfootSound a = new GreenfootSound("Invisible.mp3");
        a.setVolume(40);
        GreenfootSound b = new GreenfootSound("Sugar_Zone.mp3");
        b.setVolume(40);
        GreenfootSound c = new GreenfootSound("If_I_Had_a_Chicken.mp3");
        c.setVolume(40);
        GreenfootSound d = new GreenfootSound("Bumper_Tag.mp3");
        d.setVolume(40);
        switch(Greenfoot.getRandomNumber(4)) {
            case 0: a.playLoop(); break;
            case 1: b.playLoop(); break;
            case 2: c.playLoop(); break;
            case 3: d.playLoop(); break;
        }
    }
    
    public void setPlayer()
    {
        player1 p1 = new player1();
        addObject(p1,30,30);
        player2 p2 = new player2();
        if(cp>1) addObject(p2,30,80);
        player3 p3 = new player3();
        if(cp>2)addObject(p3,30,120);
        player4 p4 = new player4();
        if(cp>3)addObject(p4,30,170);
        player5 p5 = new player5();
        if(cp>4)addObject(p5,30,220);
        player6 p6 = new player6();
        if(cp>5)addObject(p6,30,270);
        player7 p7 = new player7();
        if(cp>6)addObject(p7,30,320);
        player8 p8 = new player8();
        if(cp>7)addObject(p8,30,370);
        
        rule.count=cp;
        rule.youLoseToo = 0;
            
        removeObject(text);
        removeObject(text2);
    }
    
    public void addItem()
    {
        addObject(new item() ,150,20);
        addObject(new item() ,150,80);
        addObject(new item() ,150,140);
        addObject(new item() ,150,200);
        addObject(new item() ,150,260);
        addObject(new item() ,150,320);
        addObject(new item() ,150,380);
        
        addObject(new item2(),300, 20);
        addObject(new item2(),300, 80);
        addObject(new item2(),300, 140);
        addObject(new item2(),300, 200);
        addObject(new item2(),300, 260);
        addObject(new item2(),300, 320);
        addObject(new item2(),300, 380);
    }
    
    public void win()
    {
        if(rule.getWin()==1)showText("p1 win",300,200);
        if(rule.getWin()==2)showText("p2 win",300,200);
        if(rule.getWin()==3)showText("p3 win",300,200);
        if(rule.getWin()==4)showText("p4 win",300,200);
        if(rule.getWin()==5)showText("p5 win",300,200);
        if(rule.getWin()==6)showText("p6 win",300,200);
        if(rule.getWin()==7)showText("p7 win",300,200);
        if(rule.getWin()==8)showText("p8 win",300,200);
    }
    
    public void die()
    {
        if(getObjects(player1.class).isEmpty()&&mode!=0)addObject(p1die,500,   30);
        else removeObject(p1die);
        if(cp>1&&getObjects(player2.class).isEmpty()&&mode!=0)addObject(p2die,500,   80);
        else removeObject(p2die);
        if(cp>2&&getObjects(player3.class).isEmpty()&&mode!=0)addObject(p3die,500,   120);
        else removeObject(p3die);
        if(cp>3&&getObjects(player4.class).isEmpty()&&mode!=0)addObject(p4die,500,   170);
        else removeObject(p4die);
        if(cp>4&&getObjects(player5.class).isEmpty()&&mode!=0)addObject(p5die,500,   220);
        else removeObject(p5die);
        if(cp>5&&getObjects(player6.class).isEmpty()&&mode!=0)addObject(p6die,500,   270);
        else removeObject(p6die);
        if(cp>6&&getObjects(player7.class).isEmpty()&&mode!=0)addObject(p7die,500,   320);
        else removeObject(p7die);
        if(cp>7&&getObjects(player8.class).isEmpty()&&mode!=0)addObject(p8die,500,   370);
        else removeObject(p8die);
    }
    
    public void rePlayer()
    {
        if(rule.back) {
            if(getObjects(player1.class).isEmpty()) {
                reCount();
                addObject(new player1(),30,30);
            }
            if(cp>1&&getObjects(player2.class).isEmpty()) {
                reCount();
                addObject(new player2(),30,80);
            }
            if(cp>2&&getObjects(player3.class).isEmpty()) {
                reCount();
                addObject(new player3(),30,120);
            }
            if(cp>3&&getObjects(player4.class).isEmpty()) {
                reCount();
                addObject(new player4(),30,170);
            }
            if(cp>4&&getObjects(player5.class).isEmpty()) {
                reCount();
                addObject(new player5(),30,220);
            }
            if(cp>5&&getObjects(player6.class).isEmpty()) {
                reCount();
                addObject(new player6(),30,270);
            }
            if(cp>6&&getObjects(player7.class).isEmpty()) {
                reCount();
                addObject(new player7(),30,320);
            }
            if(cp>7&&getObjects(player8.class).isEmpty()) {
                reCount();
                addObject(new player8(),30,370);
            }
        }
    }
    
    public void reCount()
    {
        rule.youLoseToo=0;
        rule.count++;
    }
}
