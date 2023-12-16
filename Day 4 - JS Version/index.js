import fs from "fs";

const data = fs.readFileSync("input.txt").toString();
const dataSplit = data.split("\s+");
console.log(dataSplit);