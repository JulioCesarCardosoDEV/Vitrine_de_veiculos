import "./card.css";

interface CardProps {
    price: number;
    nome: string;
    image: string;
}

export function Card({ price, image, nome }: CardProps) {
    return (
        <div className="card">
            <img src={image}/>
            <h2 className="nomeDoCarro">{nome}</h2>
            <p><b>VALOR: R$</b>{price}</p>
        </div>
    );
}

