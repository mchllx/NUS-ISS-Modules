export interface Game {
    gameId: string
    name: string
    
}

export interface Comment {
    user: string
    gameId: number
    text: string
    rating: number
}