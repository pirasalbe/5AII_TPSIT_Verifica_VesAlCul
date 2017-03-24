/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class map {
    private int counter;
    private String name;
    
    public int getCounter() { return counter; }
    public String getName() { return name; }
    public void setCounter(int counter) { this.counter=counter; }
    public void setName(String name) { this.name=name; }
    
    public map(int counter, String name){
        setCounter(counter);
        setName(name);
    }
    
    public void Increment() {counter++;}
}
