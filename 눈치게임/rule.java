import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class rule here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class rule extends Actor
{
    /**
     * Act - do whatever the rule wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 2;
    int p = 0;
    int moveplayer = 0;
    String enemy = null;
    static int youLoseToo = 0;
    static int count = 8;
    static int winner = 0;
    static boolean canMove = true;
    
    int score = 0;
    int t4 = 0;
    static boolean back = false;
    
    int t = 0;
    static boolean die = false;
    
    int i2t = 200;
    boolean canRun = true;
    int gauge = 0;
    int t3 = 600;
    int gt = 50;
    
    static boolean p1move = false;
    static boolean p2move = false;
    static boolean p3move = false;
    static boolean p4move = false;
    static boolean p5move = false;
    static boolean p6move = false;
    static boolean p7move = false;
    static boolean p8move = false;
    
    static int getItem1 = 0;
    static int getItem2 = 0;
    
    public void move(String x)
    {
        int xpos = getX();
        player();
        isMove(p, false);
        if(canMove&&canRun) {
            if(Greenfoot.isKeyDown(x)) {
                xpos+=speed;
                isMove(p, true);
            }
        }
        setLocation(xpos,getY());
        
        //if(!die) t=100;
        //time();
        
        itemRemove(p);
        itemEffect(p);
        
        gauge(p);
        debug();
        
        score();
        scoreCount();
        
        /*마이월드
        MyWorld world = (MyWorld) getWorld();
        Counter counter = world.getCounter();
        counter.bumpCount(10);
        world.getCounter().bumpCount(10);*/
        
        if(MyWorld.mode==3) ran(p);
        
        moveCount(p);
        lose(getIsMove(p), moveplayer>1, p);
        if(youLoseToo==p) lose(true, true, p);
        win(p);
    }

    public void moveCount(int i)
    {
        if(count>2) {
            if(getIsMove(i)) {
                for(int j = 1 ; j <= 8 ; j++) {
                    if(getIsMove(j)&&i!=j) {
                        moveplayer = 2;
                        youLoseToo = j;
                        //die= true;
                    }
                }
            }
        }
    }
    
    public void scoreCount()
    {
        if(getX()>1000) {
            score++;
            back = true;
        }
        resetPlayer();
    }
    
    public void resetPlayer()
    {
        if(back&&getX()!=30) {
            setLocation(30,getY());
            canRun=true;
            gauge=0;
            t3=600;
            getItem1=0;
            getItem2=0;
            speed=2;
            
            t4++;
        }
        if(t4>50||back==false) {
            back = false;
            t4=0;
        }
    }
    
    public void gauge(int p)
    {
        if(MyWorld.g==true) {
            if(getIsMove(p)) {
                gauge+=10;
                t3=600;
                gt=50;
            }
            if(gauge>1000) {
                if(t3>0) {
                    t3--;
                    canRun = false;
                } else if(t3==0) {
                    gauge=0;
                    canRun = true;
                }
            }
        }
        if(canRun==true&&getIsMove(p)==false) {
            if(gt>0) {
                gt--;
            }else if(gt==0) {
                if(gauge>0&&gauge<1000)  gauge-=2;
            }
        }
    }
    
    public void ran(int p)
    {
        if(MyWorld.t==0) {
            if(MyWorld.rN==1) {
                if(MyWorld.rN2==p) {
                    lose(true, true,p);
                }
            }
        }
    }
    
    public void itemEffect(int p)
    {
        if(p==getItem1) {
            speed=1;
        }
        if(p==getItem2) {
            if(i2t>0) {
                i2t-=1;
                canRun = false;
            } else if(i2t==0) {
                canRun = true;
            }
        }
    }
    
    public void itemRemove(int p)
    {
        Actor a = getOneIntersectingObject(item.class);
        Actor b = getOneIntersectingObject(item2.class);
        if(a!=null) {
            getWorld().removeObjects(getWorld().getObjects(item.class));
            getItem1=p;
        }
        if(b!=null) {
            getWorld().removeObjects(getWorld().getObjects(item2.class));
            getItem2=p;
        }
    }
    
    public void time()
    {
        if(die) {
            if(t>0) {
                t--;
                canMove=false;
            }  else if(t==0) {
                die = false;
                canMove=true;
            }
        }
    }
    
    public static void reset()
    {
        winner=0;
        youLoseToo=0;
        count=8;
        canMove=true;
        MyWorld.mode = 0;
        die=false;
        getItem1=0;
        getItem2=0;
        MyWorld.g=false;
        back=false;
    }
    
    public void win(int i)
    {
        if(score==2) {
            winner = i;
            canMove = false;
        }
    }
    
    public static int getWin()
    {
        return winner;
    }
    
    public void lose(boolean a, boolean b, int i)
    {
        if(a&&b) {
            remove();
            isMove(i, false);
            count--;
        }
    }
    
    public void remove() 
    {
        getWorld().removeObject(this);
    }
    
    public void debug()
    {
        if(p==1)getWorld().showText(""+gauge,200,380);
        if(p==1)getWorld().showText(""+t3   ,200,360);
        if(p==1)getWorld().showText(""+gt   ,200,340);
        if(p==2)getWorld().showText(""+gauge,250,380);
        if(p==2)getWorld().showText(""+t3   ,250,360);
        if(p==2)getWorld().showText(""+gt   ,250,340);
        if(p==3)getWorld().showText(""+gauge,300,380);
        if(p==3)getWorld().showText(""+t3   ,300,360);
        if(p==3)getWorld().showText(""+gt   ,300,340);
        if(p==4)getWorld().showText(""+gauge,350,380);
        if(p==4)getWorld().showText(""+t3   ,350,360);
        if(p==4)getWorld().showText(""+gt   ,350,340);
        if(p==5)getWorld().showText(""+gauge,400,380);
        if(p==5)getWorld().showText(""+t3   ,400,360);
        if(p==5)getWorld().showText(""+gt   ,400,340);
        if(p==6)getWorld().showText(""+gauge,450,380);
        if(p==6)getWorld().showText(""+t3   ,450,360);
        if(p==6)getWorld().showText(""+gt   ,450,340);
        if(p==7)getWorld().showText(""+gauge,500,380);
        if(p==7)getWorld().showText(""+t3   ,500,360);
        if(p==7)getWorld().showText(""+gt   ,500,340);
        if(p==8)getWorld().showText(""+gauge,550,380);
        if(p==8)getWorld().showText(""+t3   ,550,360);
        if(p==8)getWorld().showText(""+gt   ,550,340);
    }
    
    public void score()
    {
        if(p==1)getWorld().showText(""+score,45,10);
        if(p==2)getWorld().showText(""+score,120,10);
        if(p==3)getWorld().showText(""+score,195,10);
        if(p==4)getWorld().showText(""+score,270,10);
        if(p==5)getWorld().showText(""+score,345,10);
        if(p==6)getWorld().showText(""+score,420,10);
        if(p==7)getWorld().showText(""+score,495,10);
        if(p==8)getWorld().showText(""+score,570,10);
    }
    
    public void isMove(int i, boolean x)
    {
        if(i==1) p1move = x;
        if(i==2) p2move = x;
        if(i==3) p3move = x;
        if(i==4) p4move = x;
        if(i==5) p5move = x;
        if(i==6) p6move = x;
        if(i==7) p7move = x;
        if(i==8) p8move = x;
    }
    
    public boolean getIsMove(int i)
    {
        boolean result = false;
        if(i==1)result = p1move;
        if(i==2)result = p2move;
        if(i==3)result = p3move;
        if(i==4)result = p4move;
        if(i==5)result = p5move;
        if(i==6)result = p6move;
        if(i==7)result = p7move;
        if(i==8)result = p8move;
        return result;
    }
    
    public void player()
    {
        if(getY()==30)  p=1;
        if(getY()==80)  p=2;
        if(getY()==120) p=3;
        if(getY()==170) p=4;
        if(getY()==220) p=5;
        if(getY()==270) p=6;
        if(getY()==320) p=7;
        if(getY()==370) p=8;
    }
    
    public void enemy(int i)
    {
        if(i==1) enemy="a";
        if(i==2) enemy="s";
        if(i==3) enemy="d";
        if(i==4) enemy="f";
        if(i==5) enemy="g";
        if(i==6) enemy="h";
        if(i==7) enemy="j";
        if(i==8) enemy="k";
    }
}
