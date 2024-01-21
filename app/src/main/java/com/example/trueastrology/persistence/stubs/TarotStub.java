package com.example.trueastrology.persistence.stubs;

import com.example.trueastrology.objects.Tarot;
import com.example.trueastrology.persistence.ITarotPersistence;

import java.util.ArrayList;

public class TarotStub implements ITarotPersistence {
    //instance variable
    private final ArrayList<Tarot> tarotList;

    //constructor
    public TarotStub(){
        tarotList= new ArrayList<>();

        tarotList.add(new Tarot(0,
                "The Fool",
                "Ah, $. So you drew The Fool. You're encouraged to take on new adventures due to your innocence and bright spirit! Well that and the pending arrest warrant.."));
        tarotList.add(new Tarot(1, "The Magician",
                "The Magician, eh $? Can you show us the trick where you get a job and move out of your parents' basement?"));
        tarotList.add(new Tarot(2, "The High Priestess"
                , "Look, $, a High Priestess can't help you. You need divine intervention."));
        tarotList.add(new Tarot(3, "The Empress",
                "It's no wonder you drew this one, $. It really is EMPRESSIVE (get it?) how much time you waste drawing tarot cards."));
        tarotList.add(new Tarot(4, "The Emperor",
                "The great Emperor, $. God, I think humanity dodged a bullet there. Can you imagine?"));
        tarotList.add(new Tarot(5, "The Hierophant",
                "Ah yes, the mystery solver, The Hierophant. Go ahead, $, solve world hunger. We'll wait."));
        tarotList.add(new Tarot(6, "The Lovers", "Yeah, no. This one's not for you, $. Pick another card."));
        tarotList.add(new Tarot(7, "The Chariot",
                "Thank God you drew this one, $. We've been meaning to reach you about your chariot's extended warranty."));
        tarotList.add(new Tarot(8, "Strength", "Strength. Opposite of intelligence. Suits you, $."));
        tarotList.add(new Tarot(9, "The Hermit", "A hermit is someone who leads a lonely life in solitude. Except, for them, it's by choice, $."));
        tarotList.add(new Tarot(10, "Wheel of Fortune",
                "Yeah, of course. Why do something in life when you can just let a spinning wheel decide things, eh $?"));
        tarotList.add(new Tarot(11, "Justice", "This draw was no coincidence. You will be wrongfully arrested today. See you in 10 years, $."));
        tarotList.add(new Tarot(12,"The Hanged Man",
                "The hanged man represents letting things go. Given how your life is right now, $, you should have drawn this years ago. That one is on us. Sorry."));
        tarotList.add(new Tarot(13, "Death",
                "You know the rules. Get your house in order, $."));
        tarotList.add(new Tarot(14, "Temperance",
                "It's good you drew Temperance, $. You're a patient guy. The kind of guy who waits for the stop sign to turn green."));
        tarotList.add(new Tarot(15, "The Devil",
                "Yeah, the actual Devil called us and told us to get you to redraw, $. He wants nothing to do with you."));
        tarotList.add(new Tarot(16, "The Tower",
                "The tower represents change, $. Please change your clothes. We can smell you through the phone."));
        tarotList.add(new Tarot(17, "The Star",
                "The Star conveys faith and hope, $. So we're HOPING that you wont put your FAITH in the lottery tickets again, $."));
        tarotList.add(new Tarot(18, "The Moon",
                "Apparently this card represents 8 different things. So let us get this straight, $. THIS is real. But the moon landing wasn't? Ok."));
        tarotList.add(new Tarot(19, "The Sun",
                "You know, you really are like the Sun, $. No one can really look at you and everyone wants you to stay millions of miles away from them."));
        tarotList.add(new Tarot(20, "Judgement",
                "Judgement. The thing you receive from normal people when you let a deck of cards with pictures decide your life. Catch our drift, $?"));
        tarotList.add(new Tarot(21, "The World",
                "The World. Mysterious lands beyond your mom's basement. You should go explore, $! And don't forget to touch grass (its the green one on the ground)!"));
    }

    //getters
    public ArrayList<Tarot> getTarotList(){return tarotList;}

    //helper method: takes in the name of a tarot card and returns the corresponding Tarot obj
    public Tarot getTarotCard(String cardName){
        Tarot retrieved = null;
        for(int i=0; i< tarotList.size(); i++){
            if(tarotList.get(i).getCardName().equals(cardName)){
                retrieved= tarotList.get(i);
            }
        }
        return retrieved;
    }

    public String[] getTarotCardString(){
        ArrayList<Tarot> tarotCardList= this.getTarotList();
        String[] tarotCardStrings = new String[tarotCardList.size()];

        for(int i=0; i< tarotList.size(); i++){
            tarotCardStrings[i]= tarotCardList.get(i).getTarotText();
        }
        return tarotCardStrings;
    }

    public void deleteTarot(Tarot toDelete){
        for(int i=0; i< tarotList.size(); i++){
            if(tarotList.get(i).getCardNum() == toDelete.getCardNum()){
                tarotList.remove(i);
            }
        }
    }

    public void insertTarot(Tarot toAdd){
        if(!tarotList.contains(toAdd)){
            tarotList.add(toAdd);
        }
    }

    public void deleteAll(ArrayList<Tarot> list){
        for(int i=0;i<list.size();i++){
            deleteTarot(list.get(i));
        }
    }

    public void insertAll(ArrayList<Tarot> list){
        for(int i=0;i<list.size();i++){
            insertTarot(list.get(i));
        }
    }
}
