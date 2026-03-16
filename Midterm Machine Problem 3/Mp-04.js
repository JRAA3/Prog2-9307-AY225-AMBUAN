const fs = require("fs");
const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question("Enter dataset file path: ", function(path) {

    try {

        const data = fs.readFileSync(path, "utf8");

        const rows = data.split("\n");

        let validRows = 0;

        rows.forEach(row => {
            if (row.trim() !== "") {
                validRows++;
            }
        });

        console.log("Total Valid Rows:", validRows);

    } catch (error) {
        console.log("Error reading file.");
    }

    rl.close();
});