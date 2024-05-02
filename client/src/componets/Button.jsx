import '@material/web/button/_filled-button.scss'
import '@material/web/button/filled-button.js'

const Button = ({text, handleClick}) => {

    return (
        <div>
            <md-filled-button onClick = {handleClick}>{text}</md-filled-button>
        </div>
    );
};

export default Button;