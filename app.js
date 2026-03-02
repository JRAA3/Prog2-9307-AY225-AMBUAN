const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function askFilePath() {
    rl.question("Enter dataset file path: ", function(path) {
        if (fs.existsSync(path)) {
            console.log("File found! Processing...\n");
            processCSV(path);
        } else {
            console.log("Invalid file path. Try again.\n");
            askFilePath();
        }
    });
}

function processCSV(filePath) {
    try {
        const fileContent = fs.readFileSync(filePath, 'utf8');
        const lines = fileContent.split('\n');
        
        // header validation
        const header = lines[0] || '';
        if (!header.toLowerCase().includes('title') || header.split(',').length < 13) {
            console.log("Not a valid CSV dataset. Please provide a proper file.\n");
            askFilePath();
            return;
        }
        
        const dataLines = lines.slice(1);
        const monthlySales = {};
        
        dataLines.forEach(line => {
            if (line.trim() === '') return;
            try {
                const record = parseCSVLine(line);
                if (record && record.releaseDate && record.releaseDate.trim() !== '') {
                    const month = record.releaseDate.substring(0, 7);
                    monthlySales[month] = (monthlySales[month] || 0) + record.totalSales;
                }
            } catch (e) {
                return;
            }
        });
        
        const sortedMonths = Object.keys(monthlySales).sort();
        console.log("=== MONTHLY PERFORMANCE SUMMARY ===");
        console.log(String(formatField("Month", 12) + " " + formatField("Total Sales", 15)));
        console.log("=====================================");
        
        let bestMonth = "";
        let bestSales = 0;
        
        sortedMonths.forEach(month => {
            const sales = monthlySales[month];
            console.log(formatField(month, 12) + " " + sales.toFixed(2));
            if (sales > bestSales) {
                bestSales = sales;
                bestMonth = month;
            }
        });
        
        console.log("=====================================");
        console.log(`\nBest Performing Month: ${bestMonth} with ${bestSales.toFixed(2)} total sales`);
        rl.close();
    } catch (err) {
        console.log("Error reading file: " + err.message);
        rl.close();
    }
}

function parseCSVLine(line) {
    let inQuotes = false;
    let current = '';
    const parts = [];
    for (let ch of line) {
        if (ch === '"') {
            inQuotes = !inQuotes;
        } else if (ch === ',' && !inQuotes) {
            parts.push(current.trim());
            current = '';
        } else {
            current += ch;
        }
    }
    parts.push(current.trim());
    if (parts.length < 13) return null;
    const title = parts[1].replace(/"|'/g, '');
    const releaseDate = parts[12].trim();
    const totalSales = parseFloat(parts[7].trim());
    if (isNaN(totalSales)) return null;
    return { title, releaseDate, totalSales };
}

function formatField(text, width) {
    return String(text).padEnd(width);
}

askFilePath();
