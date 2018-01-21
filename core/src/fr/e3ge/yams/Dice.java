package fr.e3ge.yams;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Dice {
	
	private int value;
	private float x;
	private float y;
	private float angle;
	private Texture text;
	private ArrayList<TextureRegion> region;
	private Image image;
	private boolean keep;
	private static int global_id = 0;
	private static int nb_keep = 0;
	private static int dice_posX[][] = { 
			{163,327,146,345,284} , {152,178,123,190,182}, {243,156,149,274,154},{137,253,286,126,110}, {136,208,246,290,134},
			{292,104,166,284,182},{248,136,320,307,324},{296,134,198,128,337},{141,283,140,325,291},{256,289,173,108,305},
			{138,309,343,189,285},{217,365,250,235,101},{214,341,298,103,229},{274,136,152,147,239},{118,252,132,315,181}
	};
	
	private static int dice_posY[][] = { 
			{338,145,130,291,395} , {197,499,96,362,279}, {106,320,140,226,463}, {260,147,458,175,339}, {452,436,240,474,94},
			{362,433,184,145,315},{238,268,338,485,222},{393,477,130,234,132},{383,448,91,77,292},{220,362,402,290,105},
			{197,265,378,336,185},{86,350,266,156,263},{123,397,490,167,433},{454,400,159,286,127},{332,119,250,202,415}
			
	};
	
	public final static int dice_number_combinaison = 15;
	private int id;
	
	public Dice()
	{
		text = new Texture("dice.png");
		text.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		region = new ArrayList<TextureRegion>();
		this.keep = false;
		
		region.add(new TextureRegion(text,128,64,64,64));// 1
		region.add(new TextureRegion(text,64,64,64,64));// 2
		region.add(new TextureRegion(text,0,64,64,64)); // 3
		region.add(new TextureRegion(text,128,0,64,64));// 4
		region.add(new TextureRegion(text,64,0,64,64));// 5
		region.add(new TextureRegion(text,0,0,64,64)); // 6
		
		region.add(new TextureRegion(text,128,192,64,64));// 1
		region.add(new TextureRegion(text,64,192,64,64));// 2
		region.add(new TextureRegion(text,0,192,64,64)); // 3
		region.add(new TextureRegion(text,128,128,64,64));// 4
		region.add(new TextureRegion(text,64,128,64,64));// 5
		region.add(new TextureRegion(text,0,128,64,64)); // 6
		
		
		this.id = global_id;
		Dice.global_id++;
		
		image = new Image(region.get(this.value));
		roll(0);
	}
	
	public void roll(int combinaison)
	{
		// Si le joueur ne garde pas le dés
		if(!this.keep)
		{
			this.value = (int) (Math.random() * 6  ); 
			/*this.x = (float) (Math.random()*(350-100))+100;
			this.y = (float) (Math.random()*(500-75))+75;*/
			this.angle = (float) (Math.random()*(360));
			
			this.x = dice_posX[combinaison][this.id];
			this.y = dice_posY[combinaison][this.id];
			
			image.setScale(1f);
			image.setDrawable( new TextureRegionDrawable(region.get(this.value)));
			image.setPosition(this.x, this.y);
			image.setOrigin(region.get(0).getRegionWidth()/2, region.get(0).getRegionHeight()/2);
			image.setRotation(angle);
			
			System.out.println("ID:"+this.id+" - X:"+this.x+" - Y:"+this.y + "PATTERN :" + combinaison);
		}
	}
	
	public void keepDice()
	{
		if(this.keep)
		{
			this.keep = false;
			nb_keep--;
			image.setDrawable( new TextureRegionDrawable(region.get(this.value)));
			
		}
		else
		{
			nb_keep++;
			this.keep = true;
			image.setDrawable( new TextureRegionDrawable(region.get(this.value + 6)));
			
			if(image.getScaleX() != 0.5f)
			{
				image.setRotation(0);
				image.setScale(0.5f);
				
				switch(this.id)
				{
					case 0 : image.setPosition(522, 75 ); break;
					case 1 : image.setPosition(522 + 32, 75 ); break;
					case 2 : image.setPosition(522 + 64, 75 ); break;
					case 3 : image.setPosition(522 + 96, 75 ); break;
					case 4 : image.setPosition(522 + 128, 75 ); break;
				}
				
			}
			
		}
	}
	
	public void forceKeep()
	{
		this.keep = true;
	}
	
	public void resetKeep()
	{
		this.keep = false;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public Image getImage()
	{
		return image;
	}
		
	public void dispose()
	{
		text.dispose();
	}
}
