import java.util.ArrayList;

/**
 * 
 */

/**
 * @author baekj
 *
 */
public class ConvertToMap {

	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Player> enemyPlayers = new ArrayList<Player>();
	private Space base;
	private ArrayList<Space> flags = new ArrayList<Space>();
	private ArrayList<ArrayList<Space>> map = new ArrayList<ArrayList<Space>>();
	private int width;
	private int height;

	public ConvertToMap(String map, int width, int height){
		this.width = width;
		this.height = height;
		for(int i = 0; i < width; i++){
			this.map.add(new ArrayList<Space>());
		}
		findPOI(map);
		printOutMap(this.map);
	}
	public void findPOI(String map){
		int y = 0;
		int x = 0;

		for(int i = 0; i < map.length(); i++){
			if(y == width)
			{
				y=0;
				x++;
			}
			if(map.charAt(i) == 'X'){
				this.flags.add(new Space(new Coordinate(x, y),3, map.charAt(i)));
				this.map.get(y).add(this.flags.get(this.flags.size()-1));
			}
			else if(map.charAt(i) == 'W'){
				this.map.get(y).add(new Space(new Coordinate(x, y),1, map.charAt(i)));
			}
			else if(map.charAt(i) == '-'){
				this.map.get(y).add(new Space(new Coordinate(x, y),0, map.charAt(i)));
			}
			//players
			//with flags
			else if(map.charAt(i) >64 && map.charAt(i) < 91){
				//us
				if('0' == map.charAt(i)-65){
					this.players.add(new Player(new Coordinate(x, y),map.charAt(i),false));
					this.map.get(y).add(new Space(new Coordinate(x, y),4, map.charAt(i)));
				}
				//enemy
				else{
					this.enemyPlayers.add(new Player(new Coordinate(x, y),map.charAt(i),true));
					this.map.get(y).add(new Space(new Coordinate(x, y),4, map.charAt(i)));
				}
			}
			//without flags
			else if(map.charAt(i) > 96 && map.charAt(i) < 123){
				//us
				if('0' == map.charAt(i)-97){
					this.players.add(new Player(new Coordinate(x, y),map.charAt(i),false));
					this.map.get(y).add(new Space(new Coordinate(x, y),4, map.charAt(i)));
				}
				//enemy
				else{
					this.enemyPlayers.add(new Player(new Coordinate(x, y),map.charAt(i),true));
					this.map.get(y).add(new Space(new Coordinate(x, y),4, map.charAt(i)));
				}
			}
			//base
			else if(map.charAt(i) > 47 && map.charAt(i) < 58){
				this.base = new Space(new Coordinate(x, y),2,map.charAt(i));
				this.map.get(y).add(this.base);
			}
			y++;
		}
	}
	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	public ArrayList<Player> getEnemyPlayers(){
		return this.enemyPlayers;
	}
	
	public ArrayList<Space> getFlags(){
		return this.flags;
	}
	public Space getBase(){
		return this.base;
	}
	public ArrayList<ArrayList<Space>> getMap(){
		return this.map;
	}
	public void printOutMap(ArrayList<ArrayList<Space>> map){
		for(int i = 0; i < map.size(); i++){
			for(int z = 0; z < map.get(i).size(); z++){
				System.out.print(this.map.get(i).get(z).getString());
			}
			System.out.println();
		}	
	}
}
