export interface Pokemon {
    id: number
    name: string
    url: string
    image: string
    abilities: string[]
}

export interface Comment {
    pid: number
    id: string
    name: string
    text: string
    timestamp: Date
}