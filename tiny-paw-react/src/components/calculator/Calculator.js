import "./Calculator.css";
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { useState } from "react";
const Calculator = () =>{
    const factors = [
        {petName:"猫", calculateX: 5},
        {petName:"狗", calculateX: 10},
    ];

    const [petType, setPetType] = useState("猫");
    const [weight, setWeight] = useState('');
    const handleTypeChange = (type) =>{
        setPetType(type);
    }
    const handleWeightChange = (event) =>{
        setWeight(event.target.value);
    };

    return(
        <div className="body">
            This is CalculatorBody
            <div>
                <TextField type = "number" value = {weight} onChange={handleWeightChange}/>
                {weight}
            </div>

            <Button 
                variant = "contained" 
                color = "primary" 
                onClick= {()=> handleTypeChange("猫")}>
                猫
            </Button>

            <Button 
                variant = "contained" 
                color = "primary" 
                onClick= {()=> handleTypeChange("狗")}>
                狗
            </Button>

            <h5> {petType} {weight} </h5>
        </div>
    )
}

export default Calculator;