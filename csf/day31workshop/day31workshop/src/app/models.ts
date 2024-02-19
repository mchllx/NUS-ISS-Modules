export interface Inventory {
    image: string
    description: string 
}

export interface Cart {
    inventory: Inventory
    quantity: number
}
