function generateroles() {
    var rolestohtml = "";
    var roles = [];
    var numbers = [];
    var characters = [];

    for (var index = 0; index < 7; ++index) {
        numbers.push(Math.floor(Math.random() * (99)) + 1);
    }

    numbers.forEach(function(number) {
        if (number <= 50) {
            characters.push('T');
        } else if (number >= 51 && number <= 65) {
            characters.push('C');
        } else if (number >= 66 && number <= 75) {
            characters.push('D');
        } else if (number >= 76 && number <= 85) {
            characters.push('V');
        } else if (number >= 86 && number <= 95) {
            characters.push('M');
        } else if (number >= 96) {
            characters.push('B');
        }
    });

    var tCount = 0;
    var cCount = 0;
    var dCount = 0;
    var vCount = 0;
    var mCount = 0;
    var bCount = 0;

    characters.forEach(function(character) {
        if (character === 'T') {
            tCount++;
        } else if (character === 'C') {
            cCount++;
        } else if (character === 'D') {
            dCount++;
        } else if (character === 'V') {
            vCount++;
        } else if (character === 'M') {
            mCount++;
        }
        else if (character === 'B') {
            bCount++;
        }
    });

    if (cCount === 1) {
        roles.push("1-Shot Cop");
    } else if (cCount === 2) {
        roles.push("Cop");
    } else if (cCount === 3) {
        roles.push("Cop");
        roles.push("1-Shot Cop");
    } else if (cCount === 4) {
        roles.push("Cop");
        roles.push("Cop");
    } else if (cCount === 5) {
        roles.push("Cop");
        roles.push("Cop");
        roles.push("1-Shot Cop");
    } else if (cCount === 6) {
        roles.push("Cop");
        roles.push("Cop");
        roles.push("Cop");
    }

    if (dCount === 1) {
        roles.push("Doctor");
    } else if (dCount === 2) {
        roles.push("Doctor");
        roles.push("1-Shot Doctor");
    } else if (dCount === 3) {
        roles.push("Doctor");
        roles.push("Doctor");
    } else if (dCount === 4) {
        roles.push("Doctor");
        roles.push("Doctor");
        roles.push("1-Shot Doctor");
    } else if (dCount === 5) {
        roles.push("Doctor");
        roles.push("Doctor");
        roles.push("Doctor");
    }

    if (vCount === 1) {
        roles.push("1-Shot Vigilante");
    } else if (vCount === 2) {
        roles.push("Vigilante");
    } else if (vCount === 3) {
        roles.push("Vigilante");
        roles.push("1-Shot Vigilante");
    } else if (vCount === 4) {
        roles.push("Vigilante");
        roles.push("Vigilante");
    } else if (vCount === 5) {
        roles.push("Vigilante");
        roles.push("Vigilante");
        roles.push("1-Shot Vigilante");
    }

    if (mCount === 1) {
        roles.push("Innocent Child");
    } else if (mCount === 2) {
        roles.push("Mason");
        roles.push("Mason");
    } else if (mCount === 3) {
        roles.push("Mason");
        roles.push("Mason");
        roles.push("Innocent Child");
    } else if (mCount === 4) {
        roles.push("Mason");
        roles.push("Mason");
        roles.push("Mason");
    } else if (mCount === 5) {
        roles.push("Mason (Mason group 1)");
        roles.push("Mason (Mason group 1)");
        roles.push("Mason (Mason group 2)");
        roles.push("Mason (Mason group 2)");
    }

    if (bCount === 1) {
        roles.push("Roleblocker");
    } else if (bCount === 2) {
        roles.push("Roleblocker");
        roles.push("1-Shot Roleblocker");
    } else if (bCount === 3) {
        roles.push("Roleblocker");
        roles.push("Roleblocker");
    } else if (bCount === 4) {
        roles.push("Roleblocker");
        roles.push("Roleblocker");
        roles.push("1-Shot Roleblocker");
    }
    if (tCount === 0) {
        roles.push("Mafia Goon");
        roles.push("Mafia Roleblocker");
        roles.push("Mafia Godfather");
    } else if (tCount === 1) {
        roles.push("Mafia Goon");
        roles.push("Mafia Roleblocker");
        roles.push("Mafia Godfather");
        if (!document.rolesform.skbox.checked) {
            roles.push("Serial Killer");
        }
    } else if (tCount === 2) {
        roles.push("Mafia Goon");
        roles.push("Mafia Roleblocker");
        roles.push("Mafia Godfather");
    } else if (tCount === 3) {
        roles.push("Mafia Goon");
        roles.push("Mafia Goon");
        roles.push("Mafia Roleblocker");
        if (!document.rolesform.skbox.checked) {
            roles.push("Serial Killer");
        }
    } else if (tCount === 4) {
        roles.push("Mafia Goon");
        roles.push("Mafia Goon");
        roles.push("Mafia Roleblocker");
    } else if (tCount === 5) {
        roles.push("Mafia Goon");
        roles.push("Mafia Godfather");
        if (!document.rolesform.skbox.checked) {
            roles.push("Serial Killer");
        }
    } else if (tCount === 6) {
        roles.push("Mafia Goon");
        roles.push("Mafia Godfather");
    } else if (tCount === 7) {
        roles.push("Mafia Goon");
        roles.push("Mafia Godfather");
        if (!document.rolesform.skbox.checked) {
            roles.push("Serial Killer");
        }
    }

    while (roles.length < 13) {
        roles.push("Vanilla Townie");
    }

    roles.forEach(function(role) {
        rolestohtml += "<li>" + role + "</li>";
    });
    document.getElementById("roles").innerHTML = rolestohtml;
}