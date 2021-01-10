package org.kodluyoruz;

import java.util.ConcurrentModificationException;
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
            print(order+" raised hand.");
        }
    }
    public void takeTheOrder(String waitressName){
        synchronized (this){
            try{
                if(!raiseHand.isEmpty()){
                    for(Map.Entry<String,Boolean> mE : raiseHand.entrySet()){
                        if(mE.getValue().equals(false)){
                            takeTheOrder.put(mE.getKey(),false);
                            raiseHand.put(mE.getKey(),true);
                            print(waitressName+" has taken order of "+ mE.getKey());
                            break;
                        }
                    }
                }
            }catch (ConcurrentModificationException ex){
                print("("+waitressName+")"+ " No customers are waiting.");
            }

        }
    }
    public void preparingAnOrder(String chefName){
        synchronized (this){
            try{
                if(!takeTheOrder.isEmpty()){
                    for(Map.Entry<String,Boolean> mE : takeTheOrder.entrySet()){
                        if(mE.getValue().equals(false)){
                            preparingAnOrder.put(mE.getKey(),false);
                            takeTheOrder.put(mE.getKey(),true);
                            print(chefName+" is preparing order of "+mE.getKey());
                            break;
                        }
                    }
                }
            }catch (ConcurrentModificationException ex){
                print("("+chefName+")"+ " There is no order to be taken.");
            }
        }
    }
    public void finishedOrder(String waitressName){
        synchronized (this){
            try{
                if(!preparingAnOrder.isEmpty()){
                    for(Map.Entry<String,Boolean> mE : preparingAnOrder.entrySet()){
                        if(mE.getValue().equals(false)){
                            finished.put(mE.getKey(),false);
                            preparingAnOrder.put(mE.getKey(),true);
                            System.out.println(waitressName+" has delivered order of "+ mE.getKey());
                            break;
                        }
                    }
                }
            }catch (ConcurrentModificationException ex){
                System.out.println("("+waitressName+")"+ " There is no order to be finished");
            }
        }
    }


    public HashMap<String,Boolean> getRaiseHand() {return raiseHand;}
    public HashMap<String,Boolean> getTakeTheOrder() {return takeTheOrder;}
    public HashMap<String,Boolean> getPreparingAnOrder() {return preparingAnOrder;}
    public HashMap<String,Boolean> getFinished() {return finished;}
    public synchronized void print(String text){
        System.out.println(text);
    }
    public void deleteOrder(String order){
        raiseHand.remove(order);
        takeTheOrder.remove(order);
        preparingAnOrder.remove(order);
        finished.remove(order);
    }
}
