const fs = require("fs");
const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question("Enter dataset file path: ", function(path) {

    rl.question("Enter column number (starting from 0): ", function(column) {

        try {

            const data = fs.readFileSync(path, "utf8");

            const rows = data.split("\n");

            const unique = new Set();

            rows.forEach(row => {

                const values = row.split(",");

                if (column < values.length) {
                    unique.add(values[column]);
                }

            });

            console.log("Unique Values:");

            unique.forEach(value => console.log(value));

        } catch (error) {
            console.log("Error reading file.");
        }

        rl.close();
    });
});