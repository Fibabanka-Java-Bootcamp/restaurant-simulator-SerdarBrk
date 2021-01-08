package org.kodluyoruz;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private HashMap<String,Boolean> raiseHand;
    private HashMap<String,Boolean> takeTheOrder;
    private HashMap<String,Boolean> preparingAnOrder;
    private HashMap<String,Boolean> finished;

    public Order(){
        raiseHand =new HashMap<>();
        takeTheOrder =new HashMap<>();
        preparingAnOrder=new HashMap<>();
        finished=new HashMap<>();
    }

    public void raiseHand(String order){
        synchronized (this){
            raiseHand.put(order,false);
            System.out.println(order+" raised hand.");
        }
    }
    public void takeTheOrder(String waitressName){
        synchronized (this){

            for(Map.Entry<String,Boolean> mE : raiseHand.entrySet()){
                if(mE.getValue().equals(false)){
                    takeTheOrder.put(mE.getKey(),false);
                    raiseHand.put(mE.getKey(),true);
                    System.out.println(waitressName+" has taken order of "+ mE.getKey());
                    break;
                }
            }

        }
    }
    public void preparingAnOrder(String chefName){
        synchronized (this){
            for(Map.Entry<String,Boolean> mE : takeTheOrder.entrySet()){
                if(mE.getValue().equals(false)){
                    preparingAnOrder.put(mE.getKey(),false);
                    takeTheOrder.put(mE.getKey(),true);
                    System.out.println(chefName+" is preparing order of "+mE.getKey());
                    break;
                }
            }
        }
    }
    public void finishedOrder(String waitressName){
        synchronized (this){
            for(Map.Entry<String,Boolean> mE : preparingAnOrder.entrySet()){
                if(mE.getValue().equals(false)){
                    finished.put(mE.getKey(),false);
                    preparingAnOrder.put(mE.getKey(),true);
                    System.out.println(waitressName+" has delivered order of "+ mE.getKey());
                    break;
                }
            }
        }
    }


    public HashMap<String,Boolean> getRaiseHand() {return raiseHand;}
    public HashMap<String,Boolean> getTakeTheOrder() {return takeTheOrder;}
    public HashMap<String,Boolean> getPreparingAnOrder() {return preparingAnOrder;}
    public HashMap<String,Boolean> getFinished() {return finished;}
    public void deleteOrder(String order){
        raiseHand.remove(order);
        takeTheOrder.remove(order);
        preparingAnOrder.remove(order);
        finished.remove(order);
    }
}
