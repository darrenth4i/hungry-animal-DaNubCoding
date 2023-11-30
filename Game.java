import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Game extends World
{
    private int score;
    private Label scoreLabel;
    private Timer appleTimer;
    private boolean ended = false;
    private Timer endTimer;

    public Game()
    {
        super(600, 400, 1, false);
        this.setBackground("background.jpg");
        
        new Elephant(this);
        
        this.appleTimer = new Timer(2000);
        this.endTimer = new Timer(3000, true);

        this.score = 0;
        this.scoreLabel = new Label(this, 30, 30, this.score, 50);
    }
    
    public void act() {
        if (this.appleTimer.ended()) {
            this.spawnApple();
            this.appleTimer.restart();
        }
        
        if (this.endTimer.ended()) {
            Greenfoot.setWorld(new Menu());
        }
    }
    
    public void increaseScore(int n) {
        this.score += n;
        this.scoreLabel.setText(this.score);
    }
    
    public void spawnApple() {
        if (this.ended) return;
        Apple apple = new Apple(this);
    }
    
    public void clearApples() {
        this.removeObjects(this.getObjects(Apple.class));
    }

    public void endGame() {
        this.ended = true;
        this.clearApples();
        Label endLabel = new Label(this, getWidth() / 2, getHeight() / 2, "Game Over!", 100);
        this.endTimer.start();
    }
}
