package chess.piece.console;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import chess.piece.Piece;
import chess.piece.PieceType;

public enum ConsolePieces {
	
	
	UTF8(1, ofEntries(
		entry(PieceType.BISHOP.black,	"♝"),
		entry(PieceType.BISHOP.white,	"♗"),
		entry(PieceType.KING.black,		"♚"),
		entry(PieceType.KING.white,		"♔"),
		entry(PieceType.KNIGHT.black,	"♞"),
		entry(PieceType.KNIGHT.white,	"♘"),
		entry(PieceType.PAWN.black,		"♟"),
		entry(PieceType.PAWN.white,		"♙"),
		entry(PieceType.ROOK.black,		"♜"),
		entry(PieceType.ROOK.white,		"♖"),
		entry(PieceType.QUEEN.black,	"♛"),
		entry(PieceType.QUEEN.white,	"♕"),
		entry(PieceType.EMPTY.black,	" "),
		entry(PieceType.EMPTY.white,	" ")
	)),
	
	ASCII(2, ofEntries(
		entry(PieceType.BISHOP.black,	"Bb"),
		entry(PieceType.BISHOP.white,	"Bw"),
		entry(PieceType.KING.black,		"Kb"),
		entry(PieceType.KING.white,		"Kw"),
		entry(PieceType.KNIGHT.black,	"Nb"),
		entry(PieceType.KNIGHT.white,	"Nw"),
		entry(PieceType.PAWN.black,		"Pb"),
		entry(PieceType.PAWN.white,		"Pw"),
		entry(PieceType.ROOK.black,		"Rb"),
		entry(PieceType.ROOK.white,		"Rw"),
		entry(PieceType.QUEEN.black,	"Qb"),
		entry(PieceType.QUEEN.white,	"Qw"),
		entry(PieceType.EMPTY.black,	"  "),
		entry(PieceType.EMPTY.white,	"  ")
	));
	
	public final int length;
	public final Map<Piece, String> pieces;
	
	private ConsolePieces(int length, Map<Piece, String> pieces) {
		this.length = length;
		this.pieces = safeMap(length, pieces);
	}
	
	private static Map<Piece, String> safeMap(final int length, Map<Piece, String> pieces) {
		 final Map<Piece, String> temp = new HashMap<>(pieces);
		 temp.forEach(new BiConsumer<Piece, String>() {

			@Override
			public void accept(Piece piece, String value) {
				temp.compute(piece, new BiFunction<Piece, String, String>() {

					@Override
					public String apply(Piece piece, String value) {
						return cutToLength(length, value);
					}
				});
			}
			 
		 });
		 return Collections.unmodifiableMap(temp);
	}
	
	private static String cutToLength(int length, String s) {
		int difference = s.length() - length;
		
		if(difference < 0) {
			for(int i = 0; i < Math.abs(difference); i++) {
				s += " ";
			}
		}
		if(difference > 0) {
			s = s.substring(0, length);
		}
		
		return s;
	}
	
	public String get(Piece piece) {
		return pieces.get(piece);
	}
	
	public String getOrDefault(Piece piece, String defaultValue) {
		return pieces.getOrDefault(piece, defaultValue);
	}
	
	@SafeVarargs
	private static <K, V> Map<K, V> ofEntries(Map.Entry<K, V>...entries) {
		Map<K, V> map = new HashMap<>(entries.length);
		for(Map.Entry<K, V> entry : entries) {
			map.put(entry.getKey(), entry.getValue());
		}
		
		return map;
	}
	
	private static <K, V> Entry<K, V> entry(final K key, final V value) {
		return new Map.Entry<K, V>() {
			    @Override
			    public K getKey() {
			        return key;
			    }
			    
			    @Override
			    public V getValue() {
			        return value;
			    }

			    @Override
			    public V setValue(V value) {
			        throw new UnsupportedOperationException("not supported");
			    }
		};
	}
}
