package Models;

import java.util.ArrayList;
import java.util.Random;

public class C9Logic {

    private Random random;

    public C9Logic() {
        random = new Random();
    }

    public ArrayList<Integer> generateNumbers() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) {
            numbers.add(random.nextInt(100) + 1);
        }
        return numbers;
    }

    public ArrayList<Character> getLetters() {
        ArrayList<Integer> numbers = generateNumbers();
        ArrayList<Character> letters = new ArrayList<Character>();
        for (Integer number : numbers) {
            if (number <= 50) {
                letters.add('T');
            } else if (number >= 51 && number <= 65) {
                letters.add('C');
            } else if (number >= 66 && number <= 75) {
                letters.add('D');
            } else if (number >= 76 && number <= 85) {
                letters.add('V');
            } else if (number >= 86 && number <= 95) {
                letters.add('M');
            } else if (number >= 96) {
                letters.add('B');
            }
        }
        return letters;
    }

    public ArrayList<String> getRoles() {
        ArrayList<String> roles = new ArrayList<String>();
        ArrayList<Character> letters = getLetters();
        int tCount = 0;
        int cCount = 0;
        int dCount = 0;
        int vCount = 0;
        int mCount = 0;
        int bCount = 0;
        
        for (Character character : letters) {
            if (character == 'T') {
                tCount++;
            } else if (character == 'C') {
                cCount++;
            } else if (character == 'D') {
                dCount++;
            } else if (character == 'V') {
                vCount++;
            } else if (character == 'M') {
                mCount++;
            } else if (character == 'B') {
                bCount++;
            }
        }
        
        if (cCount == 1) {
            roles.add("1-Shot Cop");
        } else if (cCount == 2) {
            roles.add("Cop");
        } else if (cCount == 3) {
            roles.add("Cop");
            roles.add("1-Shot Cop");
        } else if (cCount == 4) {
            roles.add("Cop");
            roles.add("Cop");
        } else if (cCount == 5) {
            roles.add("Cop");
            roles.add("Cop");
            roles.add("1-Shot Cop");
        } else if (cCount == 6) {
            roles.add("Cop");
            roles.add("Cop");
            roles.add("Cop");
        }
        
        if (dCount == 1) {
            roles.add("Doctor");
        } else if (dCount == 2) {
            roles.add("Doctor");
            roles.add("1-Shot Doctor");
        } else if (dCount == 3) {
            roles.add("Doctor");
            roles.add("Doctor");
        } else if (dCount == 4) {
            roles.add("Doctor");
            roles.add("Doctor");
            roles.add("1-Shot Doctor");
        } else if (dCount == 5) {
            roles.add("Doctor");
            roles.add("Doctor");
            roles.add("Doctor");
        }
        
        if (vCount == 1) {
            roles.add("1-Shot Vigilante");
        } else if (vCount == 2) {
            roles.add("Vigilante");
        } else if (vCount == 3) {
            roles.add("Vigilante");
            roles.add("1-Shot Vigilante");
        } else if (vCount == 4) {
            roles.add("Vigilante");
            roles.add("Vigilante");
        } else if (vCount == 5) {
            roles.add("Vigilante");
            roles.add("Vigilante");
            roles.add("1-Shot Vigilante");
        }
        
        if (mCount == 1) {
            roles.add("Innocent Child");
        } else if (mCount == 2) {
            roles.add("Mason");
            roles.add("Mason");
        } else if (mCount == 3) {
            roles.add("Mason");
            roles.add("Mason");
            roles.add("Innocent Child");
        } else if (mCount == 4) {
            roles.add("Mason");
            roles.add("Mason");
            roles.add("Mason");
        } else if (mCount == 5) {
            roles.add("Mason (Mason group 1)");
            roles.add("Mason (Mason group 1)");
            roles.add("Mason (Mason group 2)");
            roles.add("Mason (Mason group 2)");
        }
        
        if (bCount == 1) {
            roles.add("Roleblocker");
        } else if (bCount == 2) {
            roles.add("Roleblocker");
            roles.add("1-Shot Roleblocker");
        } else if (bCount == 3) {
            roles.add("Roleblocker");
            roles.add("Roleblocker");
        } else if (bCount == 4) {
            roles.add("Roleblocker");
            roles.add("Roleblocker");
            roles.add("1-Shot Roleblocker");
        }
        if (tCount == 0) {
            roles.add("Mafia Goon");
            roles.add("Mafia Roleblocker");
            roles.add("Mafia Godfather");
        } else if (tCount == 1) {
            roles.add("Mafia Goon");
            roles.add("Mafia Roleblocker");
            roles.add("Mafia Godfather");
            roles.add("Serial Killer");
        } else if (tCount == 2) {
            roles.add("Mafia Goon");
            roles.add("Mafia Roleblocker");
            roles.add("Mafia Godfather");
        } else if (tCount == 3) {
            roles.add("Mafia Goon");
            roles.add("Mafia Goon");
            roles.add("Mafia Roleblocker");
            roles.add("Serial Killer");
        } else if (tCount == 4) {
            roles.add("Mafia Goon");
            roles.add("Mafia Goon");
            roles.add("Mafia Roleblocker");
        } else if (tCount == 5) {
            roles.add("Mafia Goon");
            roles.add("Mafia Godfather");
            roles.add("Serial Killer");
        } else if (tCount == 6) {
            roles.add("Mafia Goon");
            roles.add("Mafia Godfather");
        } else if (tCount == 7) {
            roles.add("Mafia Goon");
            roles.add("Mafia Godfather");
            roles.add("Serial Killer");
        }
        
        while (roles.size() < 13) {
            roles.add("Vanilla Townie");
        }

        return roles;
    }
}
